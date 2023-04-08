package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.command.DetailSkuCommand
import com.freshtuna.sharp.inventory.incoming.SkuDetailUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.result.SkuDetailResult
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SkuDetailService(
    private val findSkuPort: FindSkuPort
) : SkuDetailUseCase{

    override fun detail(command: DetailSkuCommand): SkuDetailResult {
        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(command.sellerId))
            Oh.badRequest()

        return sku.toResult()
    }
}