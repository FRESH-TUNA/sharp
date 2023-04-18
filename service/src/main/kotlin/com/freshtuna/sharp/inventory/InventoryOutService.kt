package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryLogCommand
import com.freshtuna.sharp.inventory.incoming.InventoryOutUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.InventoryOutPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryOutService(
    private val newInventoryLogPort: NewInventoryLogPort,
    private val inventoryOutPort: InventoryOutPort,
    private val findSkuPort: FindSkuPort
) : InventoryOutUseCase {

    override fun out(command: NewInventoryLogCommand, sellerId: PublicId) {

        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(!command.reason.isOUT())
            Oh.badRequest()

        inventoryOutPort.out(command)

        newInventoryLogPort.new(command)
    }
}
