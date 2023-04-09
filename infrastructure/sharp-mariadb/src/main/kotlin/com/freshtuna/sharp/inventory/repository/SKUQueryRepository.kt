package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.dto.SkuWithStocksDTO

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.page.SharpPage

interface SKUQueryRepository {

    fun search(commend: SearchSkuCommand, sellerId: PublicId): SharpPage<MariaDBSKU>

    fun skuWithStocks(skus: List<MariaDBSKU>): List<SkuWithStocksDTO>

    fun findById(id: PublicId): MariaDBSKU
}
