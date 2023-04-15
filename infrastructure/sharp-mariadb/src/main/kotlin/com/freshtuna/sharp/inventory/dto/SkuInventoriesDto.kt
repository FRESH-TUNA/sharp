package com.freshtuna.sharp.inventory.dto

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.entity.MariaDBInventory

import com.freshtuna.sharp.inventory.entity.MariaDBSKU

class SkuInventoriesDto(
    val sku: MariaDBSKU,
    val inventories: MutableList<MariaDBInventory> = mutableListOf(),
) {

    fun toDomain() = SKU(
        id = PublicId(sku.id.toString()),
        sellerId = PublicId(sku.sellerId),
        name = sku.name,
        barcode = sku.barcode,
        description = sku.description,
        spec = sku.specification.toDomain(),
        price = sku.price.toDomain(),

        expireDate = sku.expireDate,
        manufactureDate = sku.manufactureDate,
        count = inventories.sumOf { i -> if (i.status.isIN()) i.count else i.count.unaryMinus() }
    )
}
