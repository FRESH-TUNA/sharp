package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.FindSkuListPort
import com.freshtuna.sharp.item.incoming.ItemListUseCase
import com.freshtuna.sharp.item.outgoing.ItemListPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemListService(
    private val itemListPort: ItemListPort,
    private val findSkuListPort: FindSkuListPort
) : ItemListUseCase {

    override fun findAllByIds(itemIds: List<SharpID>, sellerID: SharpID): List<ItemSummary> {

        val items = itemListPort.findAllByIds(itemIds, sellerID)

        val skuIds = items.map(Item::skuId)

        val skuMap = findSkuListPort.find(skuIds).associateBy { it.id }

        return items.map { item -> item.toSummary(skuMap[item.skuId]!!) }
    }
}
