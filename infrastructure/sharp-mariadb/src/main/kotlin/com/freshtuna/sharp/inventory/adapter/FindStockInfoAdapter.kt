package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.outgoing.FindStockInfoPort
import com.freshtuna.sharp.inventory.repository.stock.StockInfoRepository
import org.springframework.stereotype.Component

@Component
class FindStockInfoAdapter(
    private val stockInfoRepository: StockInfoRepository
) : FindStockInfoPort{

    override fun find(infoId: PublicId): StockInfo
        = stockInfoRepository.findById(infoId.longId()).get().toDomain()
}
