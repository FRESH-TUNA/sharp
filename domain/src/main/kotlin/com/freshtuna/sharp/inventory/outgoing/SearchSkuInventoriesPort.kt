package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchSkuInventoriesPort {

    fun search(skuId: PublicId,
               command: SearchSkuStocksCommand,
               pageRequest: SharpPageRequest): SharpPage<Inventory>
}
