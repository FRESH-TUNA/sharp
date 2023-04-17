package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.dto.SkuInventoriesDto

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.page.SharpPage

interface SKUQueryRepository {

    fun search(commend: SearchSkuCommand, sellerId: PublicId): SharpPage<MariaDBSKU>

    fun skuWithInventories(skus: List<MariaDBSKU>): List<SkuInventoriesDto>

    fun findById(id: PublicId): MariaDBSKU
}
