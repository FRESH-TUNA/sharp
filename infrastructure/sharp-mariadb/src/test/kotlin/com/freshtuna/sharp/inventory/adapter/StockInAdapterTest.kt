package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.StockRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

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
        val skuStockInCommand = SKUStockInCommand(
            skuId = skuId,
            count = count,

            hasExpire = true,
            expireDate = LocalDateTime.now(),

            hasManufacture = true,
            manufactureDate = LocalDateTime.MIN,
        )

        val sku: MariaDBSKU = mockk()

        /**
         * when
         */
        every { skuRepository.findById(skuId.toString().toLong()) } returns Optional.of(sku)
        every { stockRepository.saveAll(any<List<MariaDBStock>>()) } returns listOf()

        /**
         * then
         */
        stockInPort.stockIn(skuStockInCommand)
    }
}
