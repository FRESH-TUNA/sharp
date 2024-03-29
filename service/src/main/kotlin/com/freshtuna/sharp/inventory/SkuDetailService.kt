package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.incoming.SkuDetailUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SkuDetailService(
    private val findSkuPort: FindSkuPort
) : SkuDetailUseCase{

    override fun detail(id: SharpID, sellerId: SharpID): SKU {

        val sku = findSkuPort.find(id)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        return sku
    }
}
