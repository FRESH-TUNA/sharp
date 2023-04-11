package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.incoming.SearchSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.result.SkuSearchResult
import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Service

@Service
class SearchSkuService(
    private val searchSkuPort: SearchSkuPort
) : SearchSkuUseCase {

    override fun search(command: SearchSkuCommand, sellerId: PublicId): SharpPage<SkuSearchResult>{

        val skuPage = searchSkuPort.search(command, sellerId)

        val skuSearchResult = skuPage.page.stream()
            .map { sku -> sku.toSearchResult() }
            .toList()

        return SharpPage(skuSearchResult, skuPage.totalCount, command.sharpPageRequest)
    }
}
