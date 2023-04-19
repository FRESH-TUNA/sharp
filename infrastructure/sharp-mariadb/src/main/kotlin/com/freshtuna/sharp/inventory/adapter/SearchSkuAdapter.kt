package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepository
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Component

@Component
class SearchSkuAdapter(
    private val repository: SKUQueryRepository
) : SearchSkuPort {

    override fun search(command: SearchSkuCommand, sellerId: SharpID): SharpPage<SKU> {

        val result = repository.search(command, sellerId)

        val skuWithInventories = repository.skuWithInventories(result.page)

        val skus = skuWithInventories.map { sku -> sku.toDomain() }.toList()

        return SharpPage(skus, result.totalCount, command.sharpPageRequest)
    }
}
