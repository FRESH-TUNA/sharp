package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.domain.Inventory

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchSkuInventoriesUseCase {

    fun search(skuId: PublicId,
               command: SearchSkuStocksCommand,
               pageRequest: SharpPageRequest,
               sellerId: PublicId): SharpPage<Inventory>
}
