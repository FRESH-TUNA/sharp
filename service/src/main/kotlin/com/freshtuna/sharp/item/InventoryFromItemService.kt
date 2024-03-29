package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.item.incoming.InventoryFromItemUseCase
import com.freshtuna.sharp.item.outgoing.ItemListPort
import com.freshtuna.sharp.item.outgoing.ShowItemPort
import com.freshtuna.sharp.item.outgoing.combo.SearchItemComboPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryFromItemService(
    private val showItemPort: ShowItemPort,
    private val itemListPort: ItemListPort,

    private val itemComboPort: SearchItemComboPort,

    private val inventoryInPort: InventoryInPort,
    private val inventoryOutPort: InventoryOutPort,
    private val newInventoryLogPort: NewInventoryLogPort
) : InventoryFromItemUseCase{

    override fun from(command: InventoryCommand, itemId: SharpID, sellerId: SharpID) {

        val item = showItemPort.show(itemId)

        if(item.sellerId != sellerId)
            Oh.badRequest()

        inventoryOutPort.out(command, item.skuId)

        newInventoryLogPort.new(command, item.skuId)

        if(!item.isCombo)
            return

        val combos = itemComboPort.search(itemId)

        val childItems = itemListPort
            .findAllByIds(combos.map(ItemCombo::itemId))
            .associateBy { it.id }

        for(combo in combos) {
            val child = childItems[combo.itemId]!!

            if(child.isCombo)
                Oh.badRequest()

            val inCommand = InventoryCommand(
                command.count*combo.amount, InventoryLogReason.SET_RELEASED, command.description)

            inventoryInPort.`in`(inCommand, child.id)

            newInventoryLogPort.new(inCommand, item.skuId)
        }
    }
}
