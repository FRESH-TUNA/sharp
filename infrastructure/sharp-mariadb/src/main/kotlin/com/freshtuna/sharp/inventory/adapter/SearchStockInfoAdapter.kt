package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.StockStatus
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
) : SearchStockInfoPort {

    override fun search(skuId: PublicId,
                        command: SearchSkuStocksCommand,
                        pageRequest: SharpPageRequest): SharpPage<StockInfo> {

        // sku 조회
        val sku = skuRepository.findById(skuId.longId())

        // stockInfo 조회
        val stockInfos = stockInfoRepository
            .findAllBySku(sku.get(), SpringPageableConverter.convert(pageRequest))

        val resultContent = stockInfos.map { stockInfo -> stockInfo.toDomain() }.toList()

        return SharpPage(resultContent, stockInfos.totalElements, pageRequest)
    }
}
