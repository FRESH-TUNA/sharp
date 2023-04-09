package com.freshtuna.sharp.inventory.dto

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.StockStatus
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStock

class SkuWithStocksDTO(
    val sku: MariaDBSKU,
    val stocks: MutableList<MariaDBStock> = mutableListOf(),
) {

    fun toDomain() = SKU(
        id = PublicId(sku.id.toString()),
        sellerId = PublicId(sku.sellerId),
        name = sku.name,
        barcode = sku.barcode,
        description = sku.description,
        spec = sku.specification.toDomain(),
        price = sku.price.toDomain(),

        availableCount = stocks.filter { stock -> stock.status == StockStatus.AVAILABLE }.size.toLong(),
        totalCount = stocks.size.toLong()
    )
}
