package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId

import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuInventoriesPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.InventoryRepository

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.util.SpringPageableConverter
import org.springframework.stereotype.Component

@Component
class SearchSkuInventoriesAdapter(
    private val skuRepository: SKURepository,
    private val inventoryRepository: InventoryRepository,
) : SearchSkuInventoriesPort {

    override fun search(skuId: PublicId,
                        command: SearchSkuStocksCommand,
                        pageRequest: SharpPageRequest): SharpPage<Inventory> {

        // sku 조회
        val sku = skuRepository.findById(skuId.longId())

        // stockInfo 조회
        val inventoriesPage = inventoryRepository
            .findAllBySku(sku.get(), SpringPageableConverter.convert(pageRequest))

        val inventories = inventoriesPage.map { i -> i.toDomain() }.toList()

        return SharpPage(inventories, inventoriesPage.totalElements, pageRequest)
    }
}
