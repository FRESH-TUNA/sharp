package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.Stock
import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.outgoing.SearchStockPort
import com.freshtuna.sharp.inventory.repository.stock.StockRepository
import org.springframework.stereotype.Component

@Component
class SearchStockAdapter(
    private val stockRepository: StockRepository
) : SearchStockPort {

    override fun findAll(stockInfos: List<StockInfo>): List<Stock>
        = stockRepository
            .findAllByInfoIdIn(stockInfos.stream().map { stockInfo -> stockInfo.id.longId() }.toList())
            .stream().map { stock -> stock.toDomain() }.toList()
}
