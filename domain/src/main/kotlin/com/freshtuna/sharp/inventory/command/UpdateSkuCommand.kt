package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import java.time.LocalDateTime

class UpdateSkuCommand(
    val name: String,
    val barcode: String,
    val description: String,

    val price: Price,
    val spec: Spec,

    val skuId: SharpID,

    val expireDate: LocalDateTime,
    val manufactureDate: LocalDateTime,
)
