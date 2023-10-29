package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.item.incoming.DeleteItemUseCase
import com.freshtuna.sharp.item.outgoing.DeleteItemPort
import com.freshtuna.sharp.item.outgoing.ShowItemPort

import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteItemService(
    private val showItemPort: ShowItemPort,
    private val skuPort: FindSkuPort,
    private val deleteItemPort: DeleteItemPort,
    private val deleteSkuPort: DeleteSkuPort
) : DeleteItemUseCase {

    override fun delete(id: SharpID, sellerID: SharpID) {

        val item = showItemPort.show(id)

        if(item.sellerId != sellerID)
            Oh.badRequest()

        val sku = skuPort.find(item.skuId)

        if(sku.count > 0)
            Oh.badRequest()

        deleteItemPort.delete(id)
        deleteSkuPort.delete(sku.id)
    }
}
