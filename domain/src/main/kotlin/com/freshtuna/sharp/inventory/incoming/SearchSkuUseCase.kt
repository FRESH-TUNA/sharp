package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.result.SkuSearchResult
import com.freshtuna.sharp.page.SharpPage

interface SearchSkuUseCase {

    fun search(command: SearchSkuCommand): SharpPage<SkuSearchResult>
}
