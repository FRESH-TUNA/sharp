package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.outgoing.FindStockInfoPort
import com.freshtuna.sharp.inventory.repository.stock.InventoryRepository
import org.springframework.stereotype.Component

@Component
class FindStockInfoAdapter(
    private val inventoryRepository: InventoryRepository
) : FindStockInfoPort{

    override fun find(infoId: PublicId): Inventory
        = inventoryRepository.findById(infoId.longId()).get().toDomain()
}
