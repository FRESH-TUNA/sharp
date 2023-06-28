package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.springframework.stereotype.Component

@Component
class InventoryInAdapter(
    private val skuRepository: SKURepository,
    private val inventoryRepository: InventoryRepository
) : InventoryInPort{
    override fun `in`(command: InventoryInOutCommand) {

        val sku = skuRepository.findById(command.skuId.longId()).get()

        val newStocks: MutableList<MariaDBInventory> = mutableListOf()

        for (i in 0 until command.count)
            newStocks.add(MariaDBInventory(sku, InventoryStatus.READY))

        inventoryRepository.saveAll(newStocks)
    }
}
