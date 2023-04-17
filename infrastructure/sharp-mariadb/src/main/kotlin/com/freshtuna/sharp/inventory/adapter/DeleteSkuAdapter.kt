package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import org.springframework.stereotype.Component

@Component
class DeleteSkuAdapter(
    private val skuRepository: SKURepository
) : DeleteSkuPort {

    override fun delete(id: PublicId) {
        skuRepository.deleteById(id.toString().toLong())
    }
}
