package com.freshtuna.sharp.price.entity

import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Embeddable
class MariaDBPrice(

    val price: BigDecimal,

    @Enumerated(EnumType.STRING)
    val currency: Currency
) {

    fun toDomain() = Price(price, currency)
}

/**
 * external
 */
fun Price.toEntity() = MariaDBPrice(this.value, this.currency)
