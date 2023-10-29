package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.FindSkuListPort
import com.freshtuna.sharp.item.incoming.ItemCombosUseCase
import com.freshtuna.sharp.item.outgoing.ItemListPort
import com.freshtuna.sharp.item.outgoing.combo.SearchItemComboPort
import org.springframework.stereotype.Service

@Service
class ItemCombosService(
    private val searchItemComboPort: SearchItemComboPort,
    private val itemListPort: ItemListPort,
    private val findSkuListPort: FindSkuListPort
) : ItemCombosUseCase {

    override fun combos(itemID: SharpID, sellerID: SharpID): List<ItemComboDetail> {

        val combos = searchItemComboPort.search(itemID)

        val childItemIds = combos.map(ItemCombo::itemId)

        val childItems = itemListPort.findAllByIds(childItemIds)

        val childSkus = findSkuListPort
            .find(childItems.map(Item::skuId))
            .associateBy { it.id }

        val childSummaries = childItems
            .map { item -> item.toSummary(childSkus[item.skuId]!!) }
            .associateBy { it.id }

        return combos.map { combo -> combo.toDetail(childSummaries[combo.itemId]!!) }
    }
}
