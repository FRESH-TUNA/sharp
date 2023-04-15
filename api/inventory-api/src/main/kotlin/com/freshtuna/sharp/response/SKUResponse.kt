package com.freshtuna.sharp.response

import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec

class SkuResponse(
    val id: String,
    val sellerId: String,

    val name: String,
    val price: Price,

    val barcode: String,

    val description: String,

    val spec: Spec,

    val count: Long
)

fun SKU.toResponse() = SkuResponse(
    id.toString(),
    sellerId.toString(),
    name,
    price,
    barcode,
    description,
    spec,
    count
)
