package com.freshtuna.sharp.spec.entity

import com.freshtuna.sharp.spec.Weight
import com.freshtuna.sharp.spec.WeightScale
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Embeddable
class MariaDBWeight(

    @Column(name = "weight_value")
    val value: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_scale")
    val scale: WeightScale
) {

    fun toDomain() = Weight(value, scale)
}

fun Weight.toEntity() = MariaDBWeight(value, scale)
