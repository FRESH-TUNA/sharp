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
    private val searchStockPort: SearchStockPort
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

        val stocks = searchStockPort.findAll(stockInfos.page)

        val stockInfoIdMap = makeStockInfoMap(stocks)

        val result = createResult(stockInfos.page, stockInfoIdMap)

        return SharpPage(result, stockInfos.totalCount, pageRequest)
    }

    private fun makeStockInfoMap(stocks: List<Stock>): Map<Long, List<Stock>> {

        val stockInfoMap = HashMap<Long, MutableList<Stock>>()

        for(stock in stocks) {
            if (stock.infoId.longId() !in stockInfoMap)
                stockInfoMap[stock.infoId.longId()] = ArrayList()

            stockInfoMap[stock.infoId.longId()]!!.add(stock)
        }

        return stockInfoMap
    }

    private fun createResult(stockInfos: List<StockInfo>,
                             stockInfoMap: Map<Long, List<Stock>>): List<StockInfoResult> {

        return stockInfos.map { stockInfo ->
            StockInfoResult.of(
                stockInfo,

                availableCount = stockInfoMap[stockInfo.id.longId()]!!
                        .filter { stock -> stock.status == StockStatus.AVAILABLE }
                        .size.toLong(),
                totalCount = stockInfoMap[stockInfo.id.longId()]!!.size.toLong()
        ) }.toList()
    }
}
