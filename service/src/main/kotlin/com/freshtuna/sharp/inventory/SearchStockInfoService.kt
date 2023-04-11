package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.incoming.SearchStockInfoUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.SearchStockInfoPort
import com.freshtuna.sharp.inventory.outgoing.SearchStockPort
import com.freshtuna.sharp.inventory.result.StockInfoResult
import com.freshtuna.sharp.oh.Oh
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

import org.springframework.stereotype.Service

@Service
class SearchStockInfoService(
    private val findSkuPort: FindSkuPort,
    private val searchStockInfoPort: SearchStockInfoPort,
) : SearchStockInfoUseCase {

    override fun search(skuId: PublicId,
                        command: SearchSkuStocksCommand,
                        pageRequest: SharpPageRequest,
                        sellerId: PublicId
    ): SharpPage<StockInfoResult> {

        val sku = findSkuPort.find(skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        val stockInfos = searchStockInfoPort.search(skuId, command, pageRequest)

        val result = stockInfos.page.stream().map { stockInfo -> stockInfo.toResult() }.toList()

        return SharpPage(result, stockInfos.totalCount, pageRequest)
    }
}
