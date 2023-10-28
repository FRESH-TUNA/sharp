package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.inventory.outgoing.UpdateSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.springframework.stereotype.Component

@Component
class UpdateSkuAdapter(
    private val skuRepository: SKURepository
) : UpdateSkuPort{

    override fun update(command: SkuCommand, id: SharpID): SKU {

        val sku = skuRepository.findById(id.longId()).get()

        sku.update(command)

        return sku.toDomain()
    }
}
