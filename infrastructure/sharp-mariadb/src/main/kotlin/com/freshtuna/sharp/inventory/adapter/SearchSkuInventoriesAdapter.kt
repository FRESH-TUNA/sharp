package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuInventoriesPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.inventory.repository.inventory.InventoryLogRepository

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.util.SpringPageableConverter
import org.springframework.stereotype.Component

@Component
class SearchSkuInventoriesAdapter(
    private val skuRepository: SKURepository,
    private val inventoryLogRepository: InventoryLogRepository,
) : SearchSkuInventoriesPort {

    override fun search(skuId: SharpID,
                        command: SearchSkuInventoryLogsCommand,
                        pageRequest: SharpPageRequest): SharpPage<InventoryLog> {

        // sku 조회
        val sku = skuRepository.findById(skuId.longId())

        // stockInfo 조회
        val inventoriesPage = inventoryLogRepository
            .findAllBySku(sku.get(), SpringPageableConverter.convert(pageRequest))

        val inventories = inventoriesPage.map { i -> i.toDomain() }.toList()

        return SharpPage(inventories, inventoriesPage.totalElements, pageRequest)
    }
}
