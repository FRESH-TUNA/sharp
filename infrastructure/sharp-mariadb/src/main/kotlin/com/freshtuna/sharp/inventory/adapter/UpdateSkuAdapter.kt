package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.inventory.outgoing.UpdateSkuPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import org.springframework.stereotype.Component

@Component
class UpdateSkuAdapter(
    private val skuRepository: SKURepository
) : UpdateSkuPort{

    override fun update(command: UpdateSkuCommand): SKU {

        val sku = skuRepository.findById(command.skuId.longId()).get()

        sku.update(command)

        return sku.toDomain()
    }
}
