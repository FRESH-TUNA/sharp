package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.entity.MariaDBInventoryLog
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.inventory.repository.inventory.InventoryLogRepository
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.util.*

class NewInventoryLogAdapterTest {

    private val skuRepository: SKURepository = mockk()

    private val logRepository: InventoryLogRepository = mockk()

    private val adapter: NewInventoryLogPort = NewInventoryLogAdapter(skuRepository, logRepository)
    @Test
    @DisplayName("재고 입/출고 기록 생성 요청 테스트")
    fun new() {

        /**
         * given
         */
        val skuId = SharpID(1L)
        val count = 3L
        val reason = InventoryLogReason.MODIFY
        val description = "초콜릿 먹고 싶다"
        val command = InventoryInOutCommand(skuId, count, reason, description)

        val skuEntity = mockk<MariaDBSKU>{
            every { id } returns skuId.longId()
        }

        every {
            skuRepository.findById(skuId.longId())
        } returns Optional.of(skuEntity)

        val logEntity = MariaDBInventoryLog(skuEntity, reason.type, reason, count, description)

        every {
            logRepository.save(any())
        } returns logEntity

        /**
         * when
         */
        val result = adapter.new(command)

        /**
         * then
         */
        assertEquals(result.reason, reason)
        assertEquals(result.count, count)
        assertEquals(result.description, description)
        assertEquals(result.type, reason.type)
    }
}
