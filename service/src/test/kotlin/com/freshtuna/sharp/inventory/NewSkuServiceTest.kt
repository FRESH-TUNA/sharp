package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal
import java.time.LocalDateTime

class NewSkuServiceTest {

    private val port: NewSkuPort = mockk()

    private val service = NewSkuService(port)

    @Test
    @DisplayName("SKU 생성 서비스 테스트")
    fun newTest() {

        /**
         * given
         */
        val command = SkuCommand(
            name = "newsku",
            barcode = "barcode",
            description = "description",
            price = Price(BigDecimal.TEN, Currency.KRW),
            spec = Spec(
                Weight(BigDecimal.TEN, WeightScale.GRAM),
                Dimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
            ),
            sellerId = SharpID("sellerId"),

            expireDate = LocalDateTime.MIN,
            manufactureDate = LocalDateTime.MIN
        )

        val newSKU = SKU(
            id = SharpID("newId"),
            name = "newsku",
            barcode = "barcode",
            description = "description",
            price = Price(BigDecimal.TEN, Currency.KRW),
            spec = Spec(
                Weight(BigDecimal.TEN, WeightScale.GRAM),
                Dimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
            ),
            sellerId = SharpID("sellerId"),

            expireDate = LocalDateTime.MIN,
            manufactureDate = LocalDateTime.MIN
        )

        /**
         * when
         */
        every { port.new(command) } returns newSKU

        /**
         * then
         */
        assertEquals(service.new(command).id, newSKU.id.toStr())
    }
}
