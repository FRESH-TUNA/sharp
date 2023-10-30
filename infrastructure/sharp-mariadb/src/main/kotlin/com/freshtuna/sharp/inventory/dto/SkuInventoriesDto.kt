package com.freshtuna.sharp.inventory.dto

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.entity.MariaDBInventory

import com.freshtuna.sharp.inventory.entity.MariaDBSKU

class SkuInventoriesDto(
    val sku: MariaDBSKU,
    val inventories: MutableList<MariaDBInventory> = mutableListOf(),
) {

    fun toDomain() = SKU(
        id = SharpID(sku.id.toString()),
        sellerId = SharpID(sku.seller.id),
        skuId = sku.name,
        barcode = sku.barcode,
        description = sku.description,
        spec = sku.specification.toDomain(),
        price = sku.price.toDomain(),

        expireDate = sku.expireDate,
        manufactureDate = sku.manufactureDate,

//        availableCount = inventories
//            .filter { i -> i.status == InventoryStatus.READY }
//            .size.toLong(),
        count = inventories.size.toLong()
    )
}
