package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import java.math.BigDecimal

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
    val dimensionScale: DimensionScale
) {
    fun toCommand(sellerId: PublicId) = NewSkuCommand(
        name = name,
        barcode = barcode,
        description = description,

        price = Price(cost, currency),
        spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),
        sellerId = sellerId
    )

    fun toUpdateCommand(sellerId: PublicId, skuId: PublicId) = UpdateSkuCommand(
        name = name,
        barcode = barcode,
        description = description,

        price = Price(cost, currency),
        spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),

        skuId = skuId,
        sellerId = sellerId
    )
}
