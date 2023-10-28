package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort

import com.freshtuna.sharp.item.incoming.InventoryToItemUseCase
import com.freshtuna.sharp.oh.Oh

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryInItemService(
    private val showItemService: ShowItemService,
    private val inventoryInPort: InventoryInPort,
    private val inventoryOutPort: InventoryOutPort,
    private val newInventoryLogPort: NewInventoryLogPort
) : InventoryToItemUseCase {

    override fun to(command: InventoryCommand, itemId: SharpID, sellerId: SharpID) {

        val item = showItemService.show(itemId, sellerId)

        val skuId = item.sku.id

        for(combo in item.combos) {
            val child = showItemService.show(combo.item.id, sellerId)

            if(child.combos.isNotEmpty())
                Oh.badRequest()

            val outCommand = InventoryCommand(
                command.count*combo.amount, InventoryLogReason.SET_EJECTED, command.description)

            inventoryOutPort.out(outCommand, child.id)
        }

        inventoryInPort.`in`(command, skuId)

        newInventoryLogPort.new(command, skuId)
    }
}
