package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.domain.*
import com.freshtuna.sharp.inventory.incoming.SearchSkuInventoriesUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.SearchSkuInventoriesPort
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

class SearchInventoryServiceTest {

    private val findSkuPort: FindSkuPort = mockk()

    private val searchSkuInventoriesPort: SearchSkuInventoriesPort = mockk()

    private val service: SearchSkuInventoriesUseCase
        = SearchSkuInventoriesService(findSkuPort, searchSkuInventoriesPort)

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

        val inventories = createSharpPage(pageRequest)
        every {
            searchSkuInventoriesPort.search(skuId, command, pageRequest)
        } returns inventories


        val result = service.search(skuId, command, pageRequest, sellerId)

        /**
         * then
         */
        assertEquals(1, result.page[0].count)
        assertEquals(1, result.page[1].count)
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
        sellerId = PublicId("sellerID"),

        expireDate = LocalDateTime.MIN,
        manufactureDate = LocalDateTime.MIN
    )

    private fun createSharpPage(pageRequest: SharpPageRequest): SharpPage<Inventory> {

        val page = listOf(
            Inventory(
                id = PublicId("1"),
                skuId = PublicId("skuId"),
                status = InventoryStatus.NEW,
                count = 1
            ),
            Inventory(
                id = PublicId("2"),
                skuId = PublicId("skuId"),
                status = InventoryStatus.OUT,
                count = 1
            )
        )

        return SharpPage(page, 2, pageRequest)
    }
}
