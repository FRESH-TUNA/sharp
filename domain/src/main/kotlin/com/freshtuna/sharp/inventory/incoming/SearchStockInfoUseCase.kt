package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.result.StockInfoResult
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchStockInfoUseCase {

    fun search(skuId: PublicId,
               command: SearchSkuStocksCommand,
               pageRequest: SharpPageRequest,
               sellerId: PublicId): SharpPage<StockInfoResult>
}
