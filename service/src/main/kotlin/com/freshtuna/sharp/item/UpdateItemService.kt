package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.UpdateSkuPort
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.item.incoming.UpdateItemUseCase
import com.freshtuna.sharp.item.outgoing.combo.DeleteItemCompositePolicyPort
import com.freshtuna.sharp.item.outgoing.combo.NewItemComboPort
import com.freshtuna.sharp.item.outgoing.ShowItemPort
import com.freshtuna.sharp.item.outgoing.UpdateItemPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateItemService(
    private val findItemPort: ShowItemPort,
    private val updatePort: UpdateItemPort,
    private val updateSkuPort: UpdateSkuPort,
    private val deleteItemCompositePolicyPort: DeleteItemCompositePolicyPort,
    private val newItemComboPort: NewItemComboPort
) : UpdateItemUseCase {

    override fun update(command: ItemCommand, itemId: SharpID, sellerId: SharpID) {

        val item = findItemPort.show(itemId)

        if(item.sellerId != sellerId)
            Oh.badRequest()

        updatePort.update(command, itemId)

        updateSkuPort.update(command.sku, item.skuId)

        deleteItemCompositePolicyPort.deleteAllByItemId(itemId)

        newItemComboPort.new(command.combos, itemId)
    }
}
