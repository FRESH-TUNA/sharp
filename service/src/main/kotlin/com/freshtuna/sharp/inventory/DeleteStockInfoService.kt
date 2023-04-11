package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.incoming.DeleteStockInfoUseCase
import com.freshtuna.sharp.inventory.outgoing.DeleteStockInfoPort
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.FindStockInfoPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteStockInfoService(
    private val findSkuPort: FindSkuPort,
    private val deleteStockInfoPort: DeleteStockInfoPort,
    private val findStockInfoPort: FindStockInfoPort,
) : DeleteStockInfoUseCase {

    override fun delete(infoId: PublicId, sellerId: PublicId) {

        val stockInfo = findStockInfoPort.find(infoId)

        val sku = findSkuPort.find(stockInfo.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(stockInfo.remainCount != stockInfo.totalCount)
            Oh.badRequest()

        deleteStockInfoPort.delete(infoId)
    }
}
