package com.freshtuna.sharp.spec.entity

import com.freshtuna.sharp.spec.Spec
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
class MariaDBSpecification(

    @Embedded
    var weight: MariaDBWeight,

    @Embedded
    var dimension: MariaDBDimension
) {

    fun toDomain() = Spec(weight.toDomain(), dimension.toDomain())
}

fun Spec.toEntity() = MariaDBSpecification(this.weight.toEntity(), this.dimension.toEntity())
