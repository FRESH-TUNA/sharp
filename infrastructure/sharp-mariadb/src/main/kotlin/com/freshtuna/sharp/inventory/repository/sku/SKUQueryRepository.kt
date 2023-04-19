package com.freshtuna.sharp.inventory.repository.sku

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.dto.SkuInventoriesDto

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.page.SharpPage

interface SKUQueryRepository {

    fun search(commend: SearchSkuCommand, sellerId: SharpID): SharpPage<MariaDBSKU>

    fun skuWithInventories(skus: List<MariaDBSKU>): List<SkuInventoriesDto>

    fun findById(id: SharpID): MariaDBSKU
}
