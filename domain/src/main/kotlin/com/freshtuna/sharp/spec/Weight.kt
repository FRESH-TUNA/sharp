package com.freshtuna.sharp.spec

import java.math.BigDecimal

class Weight(
    val value: BigDecimal,
    val scale: WeightScale
) {

    fun toGrams(): BigDecimal {
        return when (scale) {
            WeightScale.GRAM -> value
            WeightScale.KILOGRAM -> value.multiply(BigDecimal.valueOf(1000))
        }
    }
}
