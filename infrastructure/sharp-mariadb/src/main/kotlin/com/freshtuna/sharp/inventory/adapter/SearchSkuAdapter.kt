package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.repository.SKUQueryRepository
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Component

@Component
class SearchSkuAdapter(
    private val repository: SKUQueryRepository
) : SearchSkuPort {

    override fun search(command: SearchSkuCommand): SharpPage<SKU> {

        val result = repository.search(command)

        val skus = result.page.stream().map { sku -> sku.toDomain() }.toList()

        return SharpPage(skus, result.totalCount, command.sharpPageRequest)
    }
}
