package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.repository.SKUQueryRepository
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Component

@Component
class FindSkuAdapter(
    private val skuRepository: SKUQueryRepository
) : FindSkuPort {

    override fun find(skuId: PublicId)
        = skuRepository.findById(skuId).toDomain()
}
