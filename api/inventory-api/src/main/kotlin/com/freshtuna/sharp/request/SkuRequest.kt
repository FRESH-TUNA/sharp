package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import java.math.BigDecimal
import java.time.LocalDateTime

class SkuRequest(
    val name: String,
    val barcode: String,
    val description: String = "",

    val cost: BigDecimal,
    val currency: Currency,

    val weight: BigDecimal,
    val weightScale: WeightScale,

    val width: BigDecimal,
    val height: BigDecimal,
    val depth: BigDecimal,
    val dimensionScale: DimensionScale,

    val expireDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0),
    val manufactureDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0)
) {
    fun toCommand(sellerId: PublicId) = NewSkuCommand(
        name = name,
        barcode = barcode,
        description = description,

        price = Price(cost, currency),
        spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),
        sellerId = sellerId,

        expireDate = expireDate,
        manufactureDate = manufactureDate
    )

    fun toUpdateCommand(skuId: PublicId) = UpdateSkuCommand(
        name = name,
        barcode = barcode,
        description = description,

        price = Price(cost, currency),
        spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),

        skuId = skuId,

        expireDate = expireDate,
        manufactureDate = manufactureDate
    )
}
