package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.oh.exception.SharpException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class InventoryOutAdapterTest {

    private val repository: InventoryRepository = mockk()

    private val adapter: InventoryOutPort = InventoryOutAdapter(repository)
    @Test
    @DisplayName("재고 출고 요청 테스트")
    fun out() {

        /**
         * given
         */
        val skuId = SharpID(1L)
        val count = 3L
        val reason = InventoryLogReason.MODIFY
        val description = "초콜릿 먹고 싶다"

        val command = InventoryCommand(count, reason, description)

        /**
         * when
         */
        val inventories = mutableListOf<MariaDBInventory>()

        inventories.add(mockk())
        inventories.add(mockk())
        inventories.add(mockk())

        every {
            repository.findAllBySkuIdAndStatus(skuId.longId(), InventoryStatus.READY, count)
        } returns inventories

        every {
            repository.deleteAll(inventories)
        } returns Unit

        /**
         * then
         */
        adapter.out(command, skuId)
    }

    @Test
    @DisplayName("출고할 재고가 부족하면 출고할수 없다")
    fun outFailWhenNotEnoughStock() {

        /**
         * given
         */
        val skuId = SharpID(1L)
        val count = 3L
        val reason = InventoryLogReason.MODIFY
        val description = "초콜릿 먹고 싶다"

        val command = InventoryCommand(count, reason, description)

        /**
         * when
         */
        val inventories = mutableListOf<MariaDBInventory>()

        inventories.add(mockk())
        inventories.add(mockk())

        every {
            repository.findAllBySkuIdAndStatus(skuId.longId(), InventoryStatus.READY, count)
        } returns inventories

        every {
            repository.deleteAll(inventories)
        } returns Unit

        /**
         * then
         */
        assertThrows<SharpException> { adapter.out(command, skuId) }
    }
}
