package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.repository.SKUQueryRepository
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Component

@Component
class SearchSkuAdapter(
    private val repository: SKUQueryRepository
) : SearchSkuPort {

    override fun search(command: SearchSkuCommand, sellerId: PublicId): SharpPage<SKU> {

        val result = repository.search(command, sellerId)

        val skuWithStocks = repository.skuWithInventories(result.page)

        val skus = skuWithStocks.stream().map { sku -> sku.toDomain() }.toList()

        return SharpPage(skus, result.totalCount, command.sharpPageRequest)
    }
}
