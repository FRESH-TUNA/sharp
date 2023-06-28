package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.spec.*
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class MariaDBSKUTest {

    @Test
    fun update() {
        /**
         * given
         */
        val sellerId = UUID.randomUUID()
        val name = "before"
        val barcode = "before"
        val description = "before"
        val beforePrice = MariaDBPrice(mockk(), mockk())
        val specification = MariaDBSpecification(mockk(), mockk())
        val expireDate = LocalDateTime.MIN
        val manufactureDate = LocalDateTime.MIN

        val sku = MariaDBSKU(
            sellerId = sellerId,
            name = name,
            barcode = barcode,
            description = description,
            price = beforePrice,
            specification = specification,
            expireDate = expireDate,
            manufactureDate = manufactureDate
        )

        val newName = "after"
        val newBarcode = "after"
        val newDescription = "after"

        val newValue = BigDecimal.valueOf(1000)
        val newCurrency = Currency.KRW
        val newWeightScale = WeightScale.GRAM
        val newDimensionScale = DimensionScale.CM

        val newBeforePrice = Price(newValue, Currency.KRW)
        val newSpecification = Spec(
            Weight(newValue, WeightScale.GRAM),
            Dimension(newValue, newValue, newValue, newDimensionScale)
        )
        val newExpireDate = LocalDateTime.now()
        val newManufactureDate = LocalDateTime.now()

        val command = UpdateSkuCommand(
            name = newName,
            barcode = newBarcode,
            description = newDescription,
            price = newBeforePrice,
            spec = newSpecification,
            skuId = mockk(),
            newExpireDate, newManufactureDate
        )

        /**
         * when
         */
        sku.update(command)

        /**
         * then
         */
        assertEquals(sku.barcode, newBarcode)
        assertEquals(sku.description, newDescription)
        assertEquals(sku.expireDate, newExpireDate)
        assertEquals(sku.manufactureDate, newManufactureDate)
        assertEquals(sku.name, newName)

        //weight
        assertEquals(sku.specification.weight.value, newValue)
        assertEquals(sku.specification.weight.scale, newWeightScale)

        //dimension
        assertEquals(sku.specification.dimension.scale, newDimensionScale)
        assertEquals(sku.specification.dimension.depth, newValue)
        assertEquals(sku.specification.dimension.height, newValue)
        assertEquals(sku.specification.dimension.width, newValue)

        //currency
        assertEquals(sku.price.currency, newCurrency)
        assertEquals(sku.price.price, newValue)
    }
}
