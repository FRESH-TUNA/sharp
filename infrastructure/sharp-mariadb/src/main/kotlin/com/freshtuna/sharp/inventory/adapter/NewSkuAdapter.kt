package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.entity.toEntity
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.springframework.stereotype.Component

@Component
class NewSkuAdapter(
    private val repository: SKURepository,
    private val sellerRepository: SellerRepository
) : NewSkuPort {

    override fun new(command: NewSkuCommand): SKU {
        val seller = sellerRepository.getReferenceById(command.sellerId.longId())
        return repository.save(command.toEntity(seller)).toDomain()
    }
}
