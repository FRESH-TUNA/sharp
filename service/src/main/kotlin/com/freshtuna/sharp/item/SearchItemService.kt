package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.FindSkuListPort

import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.item.incoming.SearchItemUseCase
import com.freshtuna.sharp.item.outgoing.SearchItemPort
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Service

@Service
class SearchItemService(
    private val searchItemPort: SearchItemPort,
    private val findSkuListPort: FindSkuListPort
): SearchItemUseCase {

    override fun search(command: SearchItemCommand, sellerId: SharpID): SharpPage<ItemSummary> {

        val itemPage = searchItemPort.search(command, sellerId)

        val skuIds = itemPage.page.map(Item::skuId)

        val skuMap = findSkuListPort.find(skuIds).associateBy { it.id }

        val itemSummaryPage = itemPage.page
            .map { item -> item.toSummary(skuMap[item.skuId]!!) }
            .toList()

        return SharpPage(itemSummaryPage, itemPage.totalCount, command.pageRequest)
    }
}
