package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec

class UpdateSkuCommand(
    val name: String,
    val barcode: String,
    val description: String,

    val price: Price,
    val spec: Spec,

    val skuId: PublicId
)
