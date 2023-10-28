package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.dto.SkuInventoriesDto
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepository

import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.ItemSummary
import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.item.entity.ItemEntity
import com.freshtuna.sharp.item.outgoing.SearchItemPort

import com.freshtuna.sharp.item.repository.ItemSearchRepository
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Component

@Component
class SearchItemAdapter(
    private val itemRepository: ItemSearchRepository
): SearchItemPort {

    override fun search(command: SearchItemCommand, sellerId: SharpID): SharpPage<Item> {

        val searchResult = itemRepository.search(command, sellerId)

        val items = searchResult
            .page.map { item ->  item.toDomain() }
            .toList()

        return SharpPage(items, searchResult.totalCount, command.pageRequest)
    }
}
