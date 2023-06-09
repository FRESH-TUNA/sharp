package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
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

        val command = InventoryInOutCommand(skuId, count, reason, description)

        /**
         * when
         */
        val page = pageOfSuccess(count)

        every {
            repository.findBySkuIdAndStatus(skuId.longId(), InventoryStatus.READY, any())
        } returns page

        every {
            repository.deleteAll(page.content)
        } returns Unit

        /**
         * then
         */
        adapter.out(command)
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

        val command = InventoryInOutCommand(skuId, count, reason, description)

        /**
         * when
         */
        val page = pageOfSuccess(count)

        every {
            repository.findBySkuIdAndStatus(skuId.longId(), InventoryStatus.READY, any())
        } returns Page.empty()

        every {
            repository.deleteAll(page.content)
        } returns Unit

        /**
         * then
         */
        assertThrows<SharpException> { adapter.out(command) }
    }

    private fun pageOfSuccess(count: Long): Page<MariaDBInventory> {
        val inventories = mutableListOf<MariaDBInventory>()

        for (i in 0 until count)
            inventories.add(MariaDBInventory(mockk(), InventoryStatus.READY))

        return PageImpl(inventories)
    }
}
