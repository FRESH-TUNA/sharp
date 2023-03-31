package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Component

@Component
class FindSkuAdapter(
    private val skuRepository: SKURepository
) : FindSkuPort {

    override fun find(skuId: PublicId): SKU {
        val sku = skuRepository.findById(skuId.toString().toLong())

        if(!sku.isPresent)
            Oh.badRequest()

        return sku.get().toDomain()
    }
}
