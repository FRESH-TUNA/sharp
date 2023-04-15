package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.outgoing.NewInventoryPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.InventoryRepository
import org.springframework.stereotype.Component

@Component
class NewInventoryAdapter(
    private val skuRepository: SKURepository,
    private val inventoryRepository: InventoryRepository
) : NewInventoryPort {

    override fun new(command: NewInventoryCommand): Inventory {

        val sku = skuRepository.findById(command.skuId.longId()).get()

        var info = MariaDBInventory.of(sku, command)

        return inventoryRepository.save(info).toDomain()
    }
}
