package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.outgoing.FindSkuListPort
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.springframework.stereotype.Component

@Component
class FindSkuListAdapter(
    private val repository: SKURepository,
    private val queryRepository: SKUQueryRepository
): FindSkuListPort{

    override fun find(ids: List<SharpID>): List<SKU> {

        val skus = repository.findAllById(ids.map { id -> id.longId() })

        val skuWithInventories = queryRepository.skuWithInventories(skus)

        return skuWithInventories
            .map { sku -> sku.toDomain() }
            .toList()
    }
}
