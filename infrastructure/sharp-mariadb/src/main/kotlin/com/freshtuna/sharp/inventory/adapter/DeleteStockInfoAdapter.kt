package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.outgoing.DeleteStockInfoPort
import com.freshtuna.sharp.inventory.repository.stock.StockInfoRepository
import com.freshtuna.sharp.inventory.repository.stock.StockRepository
import org.springframework.stereotype.Component

@Component
class DeleteStockInfoAdapter(
    private val stockInfoRepository: StockInfoRepository,
    private val stockRepository: StockRepository,
): DeleteStockInfoPort{

    override fun delete(infoId: PublicId) {

        val stockInfo = stockInfoRepository.findById(infoId.longId())

        stockRepository.deleteAll(stockInfo.get().stocks)

        stockInfoRepository.delete(stockInfo.get())
    }
}
