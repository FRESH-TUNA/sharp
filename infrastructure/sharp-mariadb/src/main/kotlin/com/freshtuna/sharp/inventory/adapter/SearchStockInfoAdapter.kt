package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId

import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.entity.MariaDBStockInfo
import com.freshtuna.sharp.inventory.outgoing.SearchStockInfoPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.StockInfoRepository
import com.freshtuna.sharp.inventory.repository.stock.StockRepository

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.util.SpringPageableConverter
import org.springframework.stereotype.Component

@Component
class SearchStockInfoAdapter(
    private val skuRepository: SKURepository,
    private val stockInfoRepository: StockInfoRepository,
    private val stockRepository: StockRepository
) : SearchStockInfoPort {

    override fun search(skuId: PublicId,
                        command: SearchSkuStocksCommand,
                        pageRequest: SharpPageRequest): SharpPage<StockInfo> {

        // sku 조회
        val sku = skuRepository.findById(skuId.longId())

        // stockInfo 조회
        val stockInfos = stockInfoRepository
            .findAllBySku(sku.get(), SpringPageableConverter.convert(pageRequest))

        val stocks = stockRepository.findAllByInfoIn(stockInfos.toList())

        val stockInfoIdMap = makeStockInfoMap(stocks)

        val result = createResult(stockInfos.content, stockInfoIdMap)

        return SharpPage(result, stockInfos.totalElements, pageRequest)
    }

    private fun makeStockInfoMap(stocks: List<MariaDBStock>): Map<MariaDBStockInfo, List<MariaDBStock>> {

        val stockInfoMap = HashMap<MariaDBStockInfo, MutableList<MariaDBStock>>()

        for(stock in stocks) {
            if (stock.info !in stockInfoMap)
                stockInfoMap[stock.info] = ArrayList()

            stockInfoMap[stock.info]!!.add(stock)
        }

        return stockInfoMap
    }

    private fun createResult(stockInfos: List<MariaDBStockInfo>,
                             stockInfoMap: Map<MariaDBStockInfo, List<MariaDBStock>>): List<StockInfo> {

        return stockInfos.map { stockInfo ->
            stockInfo.toDomain(
                remainCount = stockInfoMap[stockInfo]!!
                    .filter { stock -> stock.status == com.freshtuna.sharp.inventory.StockStatus.AVAILABLE }
                    .size.toLong(),
                totalCount = stockInfoMap[stockInfo]!!.size.toLong()
            )}.toList()
    }
}
