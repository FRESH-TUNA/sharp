package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.StockRepository
import org.springframework.stereotype.Component

@Component
class StockInAdapter(
    private val skuRepository: SKURepository,
    private val stockRepository: StockRepository
) : StockInPort {

    override fun stockIn(command: SKUStockInCommand) {
        val sku = skuRepository.findById(command.skuId.toString().toLong()).get()
        val newStocks = List(command.count.toInt()) { MariaDBStock.newStock(sku, command) }

        stockRepository.saveAll(newStocks)
    }
}
