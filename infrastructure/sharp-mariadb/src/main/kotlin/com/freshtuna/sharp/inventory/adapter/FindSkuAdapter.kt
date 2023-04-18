package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepository
import org.springframework.stereotype.Component

@Component
class FindSkuAdapter(
    private val skuRepository: SKUQueryRepository
) : FindSkuPort {

    override fun find(skuId: PublicId)
        = skuRepository.findById(skuId).toDomain()
}
