package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.util.*

class InventoryInAdapterTest {

    private val skuRepository: SKURepository = mockk()

    private val inventoryRepository: InventoryRepository = mockk()

    private val adapter: InventoryInPort = InventoryInAdapter(skuRepository, inventoryRepository)

    @Test
    @DisplayName("재고 입고 요청 테스트")
    fun `in`() {

        /**
         * given
         */
        val skuId = SharpID(1L)
        val count = 3L
        val reason = InventoryLogReason.RETURN
        val description = "초콜릿 먹고 싶다"

        val command = InventoryCommand(count, reason, description)

        /**
         * when
         */
        val sku: MariaDBSKU = mockk()

        every {
            skuRepository.findById(skuId.longId())
        } returns Optional.of(sku)

        every {
            inventoryRepository.saveAll(any<Iterable<MariaDBInventory>>())
        } returns mockk()

        /**
         * then
         */
        adapter.`in`(command, skuId)
    }
}