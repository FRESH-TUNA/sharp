package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.springframework.stereotype.Component

@Component
class DeleteSkuAdapter(
    private val skuRepository: SKURepository
) : DeleteSkuPort {

    override fun delete(id: SharpID) {
        skuRepository.deleteById(id.longId())
    }
}
