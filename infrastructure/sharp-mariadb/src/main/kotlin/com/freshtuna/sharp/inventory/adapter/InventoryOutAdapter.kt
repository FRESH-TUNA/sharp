package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.oh.Oh
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class InventoryOutAdapter(
    private val inventoryRepository: InventoryRepository
) : InventoryOutPort {

    override fun out(command: InventoryInOutCommand) {

        val pageRequest = PageRequest.of(0, command.count.toInt())

        val inventories = inventoryRepository.findBySkuIdAndStatus(
            command.skuId.longId(),
            InventoryStatus.READY,
            pageRequest
        )

        if(inventories.size != command.count.toInt())
            Oh.badRequest()

        inventoryRepository.deleteAll(inventories.content)
    }
}
