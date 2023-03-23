package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.inventory.repository.MariaDBSKURepository
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.spec.*
import com.freshtuna.sharp.spec.entity.MariaDBDimension
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import com.freshtuna.sharp.spec.entity.MariaDBWeight
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal

class NewSkuAdapterTest {

    private val repository: MariaDBSKURepository = mockk()

    private val adapter: NewSkuPort = NewSkuAdapter(repository)

    @Test
    @DisplayName("SKU 생성 테스트")
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

        val newMariaDBSKU = MariaDBSKU(
            name = "newsku",
            barcode = "barcode",
            description = "description",
            price = MariaDBPrice(BigDecimal.TEN, Currency.KOW),
            specification = MariaDBSpecification(
                MariaDBWeight(BigDecimal.TEN, WeightScale.GRAM),
                MariaDBDimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
            )
        )

        /**
         * when
         */
        every { repository.save(any()) } returns newMariaDBSKU

        /**
         * then
         */
        assertEquals(adapter.new(command) is SKU, true)
    }
}
