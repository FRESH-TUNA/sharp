package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.outgoing.FindStockInfoPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryLogRepository
import org.springframework.stereotype.Component

@Component
class FindInventoryLogAdapter(
    private val inventoryLogRepository: InventoryLogRepository
) : FindStockInfoPort{

    override fun find(infoId: SharpID): InventoryLog
        = inventoryLogRepository.findById(infoId.longId()).get().toDomain()
}
