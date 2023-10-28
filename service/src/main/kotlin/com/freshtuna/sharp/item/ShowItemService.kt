package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.item.incoming.ShowItemUseCase
import com.freshtuna.sharp.item.outgoing.composite.SearchItemCompositePolicyPort
import com.freshtuna.sharp.item.outgoing.ShowItemPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service

@Service
class ShowItemService(
    private val showItemPort: ShowItemPort,
    private val searchItemComposiitePolicyPort: SearchItemCompositePolicyPort,
    private val skuPort: FindSkuPort,
) : ShowItemUseCase{

    override fun show(itemID: SharpID, sellerId: SharpID): ItemDetail {
        val item = showItemPort.show(itemID)

        if(item.sellerId != sellerId)
            Oh.badRequest()

        val sku = skuPort.find(itemID)

        val policies = searchItemComposiitePolicyPort.search(itemID)

        return ItemDetail(item, sku, policies)
    }
}
