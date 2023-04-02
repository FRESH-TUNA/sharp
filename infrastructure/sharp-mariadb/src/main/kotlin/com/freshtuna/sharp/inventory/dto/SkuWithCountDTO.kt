package com.freshtuna.sharp.inventory.dto

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.entity.MariaDBSKU

class SkuWithCountDTO(
    val sku: MariaDBSKU,
    val count: Int
) {

    fun toDomain() = SKU(
        id = PublicId(sku.id.toString()),
        sellerId = PublicId(sku.sellerId),
        name = sku.name,
        barcode = sku.barcode,
        description = sku.description,
        spec = sku.specification.toDomain(),
        price = sku.price.toDomain(),
        count = count.toLong()
    )
}
