package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal

class NewSkuServiceTest {

    private val port: NewSkuPort = mockk()

    private val service = NewSkuService(port)

    @Test
    @DisplayName("SKU 생성 서비스 테스트")
    fun newTest() {

        /**
         * given
         */
        val command = NewSkuCommand(
            name = "newsku",
            barcode = "barcode",
            description = "description",
            price = Price(BigDecimal.TEN, Currency.KOW),
            spec = Spec(
                Weight(BigDecimal.TEN, WeightScale.GRAM),
                Dimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
            )
        )

        val newSKU = SKU(
            id = PublicId("newId"),
            name = "newsku",
            barcode = "barcode",
            description = "description",
            price = Price(BigDecimal.TEN, Currency.KOW),
            spec = Spec(
                Weight(BigDecimal.TEN, WeightScale.GRAM),
                Dimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
            )
        )

        /**
         * when
         */
        every { port.new(command) } returns newSKU

        /**
         * then
         */
        assertEquals(service.new(command), newSKU)
    }
}
