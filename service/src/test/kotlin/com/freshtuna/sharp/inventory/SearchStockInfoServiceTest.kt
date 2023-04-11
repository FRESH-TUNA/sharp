package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.incoming.SearchStockInfoUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.SearchStockInfoPort
import com.freshtuna.sharp.inventory.outgoing.SearchStockPort
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.LocalDateTime

class SearchStockInfoServiceTest {

    private val findSkuPort: FindSkuPort = mockk()

    private val searchStockInfoPort: SearchStockInfoPort = mockk()

    private val searchStockPort: SearchStockPort = mockk()

    private val service: SearchStockInfoUseCase
        = SearchStockInfoService(findSkuPort, searchStockInfoPort, searchStockPort)

    @Test
    fun search() {

        /**
         * given
         */
        val skuId = PublicId("skuId")
        val command = SearchSkuStocksCommand()
        val pageRequest =  SharpPageRequest()
        val sellerId = PublicId("sellerID")

        /**
         * when
         */
        val sku = createSKU()
        every { findSkuPort.find(skuId) } returns sku

        val stockInfos = createSharpPage(pageRequest)
        every {
            searchStockInfoPort.search(skuId, command, pageRequest)
        } returns stockInfos

        val stocks = createStocks()
        every {
            searchStockPort.findAll(stockInfos.page)
        } returns stocks

        val result = service.search(skuId, command, pageRequest, sellerId)

        /**
         * then
         */
        assertEquals(1, result.page[0].availableCount)
        assertEquals(2, result.page[0].totalCount)
    }

    private fun createSKU() = SKU(
        id = PublicId("newId"),
        name = "newsku",
        barcode = "barcode",
        description = "description",
        price = Price(BigDecimal.TEN, Currency.KRW),
        spec = Spec(
            Weight(BigDecimal.TEN, WeightScale.GRAM),
            Dimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
        ),
        sellerId = PublicId("sellerID")
    )

    private fun createSharpPage(pageRequest: SharpPageRequest): SharpPage<StockInfo> {
        val totalCount = 3L
        val page = listOf(
            StockInfo(
                id = PublicId("1"),
                skuId = PublicId("skuId"),

                requestStatus = StockRequestStatus.COMPLETE,

                hasExpire = true,
                expireDate = LocalDateTime.now(),

                hasManufacture = true,
                manufactureDate = LocalDateTime.now()
        ))

        return SharpPage(page, totalCount, pageRequest)
    }

    private fun createStocks(): List<Stock> {
        return listOf(
            Stock(
                id = PublicId("id"),
                skuId = PublicId("skuId"),
                infoId = PublicId("1"),
                status = StockStatus.AVAILABLE
            ),
            Stock(
                id = PublicId("id"),
                skuId = PublicId("skuId"),
                infoId = PublicId("1"),
                status = StockStatus.PACKAGED
            )
        )
    }
}
