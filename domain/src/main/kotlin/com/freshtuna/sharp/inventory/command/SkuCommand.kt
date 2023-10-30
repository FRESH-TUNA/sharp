package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import java.time.LocalDateTime

class SkuCommand(
    val skuId: String,
    val barcode: String,
    val description: String = "",

    val price: Price,
    val spec: Spec,

    val expireDate: LocalDateTime,
    val manufactureDate: LocalDateTime
) {

}
