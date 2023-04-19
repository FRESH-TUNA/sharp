package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.entity.MariaDBInventoryLog
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.inventory.repository.inventory.InventoryLogRepository
import org.springframework.stereotype.Component

@Component
class NewInventoryLogAdapter(
    private val skuRepository: SKURepository,
    private val inventoryLogRepository: InventoryLogRepository
) : NewInventoryLogPort {

    override fun new(command: InventoryInOutCommand): InventoryLog {

        val sku = skuRepository.findById(command.skuId.longId()).get()

        var info = MariaDBInventoryLog.of(sku, command)

        return inventoryLogRepository.save(info).toDomain()
    }
}
