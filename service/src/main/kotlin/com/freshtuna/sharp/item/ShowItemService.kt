package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort

import com.freshtuna.sharp.item.incoming.ItemListUseCase
import com.freshtuna.sharp.item.incoming.ShowItemUseCase
import com.freshtuna.sharp.item.outgoing.ShowItemPort
import com.freshtuna.sharp.item.outgoing.composite.SearchItemComboPort

import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ShowItemService(
    private val itemListUseCase: ItemListUseCase,

    private val showItemPort: ShowItemPort,
    private val skuPort: FindSkuPort,
    private val searchItemComboPort: SearchItemComboPort
) : ShowItemUseCase{

    override fun show(itemID: SharpID, sellerId: SharpID): ItemDetail {
        val item = showItemPort.show(itemID)

        if(item.sellerId != sellerId)
            Oh.badRequest()

        val sku = skuPort.find(itemID)

        val combos = searchItemComboPort.search(item.id)

        val childItemIds = combos.map(ItemCombo::itemId)

        val summaries = itemListUseCase
            .findAllByIds(childItemIds, sellerId)
            .associateBy { it.id }

        val policies = combos.map { combo -> combo.toDetail(summaries[combo.itemId]!!) }

        return ItemDetail(item, sku, policies)
    }
}
