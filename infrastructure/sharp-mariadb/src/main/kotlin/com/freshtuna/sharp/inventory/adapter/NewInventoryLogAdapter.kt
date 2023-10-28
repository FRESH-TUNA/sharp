package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.entity.MariaDBInventoryLog
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.inventory.repository.inventory.InventoryLogRepository
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Component

@Component
class NewInventoryLogAdapter(
    private val skuRepository: SKURepository,
    private val inventoryLogRepository: InventoryLogRepository
) : NewInventoryLogPort {

    override fun new(command: InventoryCommand, skuId: SharpID): InventoryLog {

        val sku = skuRepository.findById(skuId.longId())

        if(sku.isEmpty)
            Oh.badRequest()

        var info = MariaDBInventoryLog.of(sku.get(), command)

        return inventoryLogRepository.save(info).toDomain()
    }
}
