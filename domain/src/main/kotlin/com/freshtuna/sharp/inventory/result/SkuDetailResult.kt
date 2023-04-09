package com.freshtuna.sharp.inventory.result

import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec

class SkuDetailResult(
    val id: String,
    val sellerId: String,

    val name: String,
    val price: Price,

    val barcode: String,

    val description: String,

    val spec: Spec,

    val availableCount: Long,
    val totalCount: Long
)
