package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.StockInCommand
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.StockRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StockInAdapterTest {

    private val skuRepository: SKURepository = mockk()
    private val stockRepository: StockRepository = mockk()

    private val stockInPort: StockInPort = StockInAdapter(skuRepository, stockRepository)

    @Test
    @DisplayName("재고 입고 테스트")
    fun stockIn() {
        /**
         * given
         */
        val skuId = PublicId("1")
        val count = 5L
        val stockInCommand = StockInCommand(skuId, count)

        val sku: MariaDBSKU = mockk()

        /**
         * when
         */
        every { skuRepository.getReferenceById(skuId.toString().toLong()) } returns sku
        every { stockRepository.saveAll(any<List<MariaDBStock>>()) } returns listOf()

        /**
         * then
         */
        stockInPort.stockIn(stockInCommand)
    }
}
