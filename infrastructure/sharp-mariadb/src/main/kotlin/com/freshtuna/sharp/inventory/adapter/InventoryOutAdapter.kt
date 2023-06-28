package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Component

@Component
class InventoryOutAdapter(
    private val inventoryRepository: InventoryRepository
) : InventoryOutPort {

    override fun out(command: InventoryInOutCommand) {

        val totalCount = inventoryRepository.countBySkuIdAndStatus(
            command.skuId.longId(),
            InventoryStatus.READY,
        )

        if(totalCount < command.count)
            Oh.badRequest()

        inventoryRepository.deleteBySkuIdWithCount(command.skuId.longId(), command.count)
    }
}
