package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.dto.SkuWithCountDTO
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.page.SharpPage

interface SKUQueryRepository {

    fun search(commend: SearchSkuCommand): SharpPage<SkuWithCountDTO>

    fun findById(id: PublicId): SkuWithCountDTO
}
