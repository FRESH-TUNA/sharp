package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.StockInCommand
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.StockRepository
import org.springframework.stereotype.Component

@Component
class StockInAdapter(
    private val skuRepository: SKURepository,
    private val stockRepository: StockRepository
) : StockInPort {

    override fun stockIn(command: StockInCommand) {
        val sku = skuRepository.getReferenceById(command.skuId.toString().toLong())
        val newStocks = ArrayList<MariaDBStock>(command.count.toInt())

        for (i in 1..command.count)
            newStocks.add(MariaDBStock(sku))

        stockRepository.saveAll(newStocks)
    }
}
