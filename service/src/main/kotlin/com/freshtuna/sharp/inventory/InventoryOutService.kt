package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.incoming.InventoryOutUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryOutService(
    private val newInventoryPort: NewInventoryPort,
    private val findSkuPort: FindSkuPort
) : InventoryOutUseCase {

    override fun new(command: NewInventoryCommand, sellerId: PublicId) {

        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(!command.status.isOUT())
            Oh.badRequest()

        newInventoryPort.new(command)
    }
}
