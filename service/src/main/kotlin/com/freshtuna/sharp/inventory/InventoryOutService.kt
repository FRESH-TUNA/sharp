package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
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

    override fun out(command: InventoryInOutCommand, sellerId: SharpID) {

        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(!command.reason.isOUT())
            Oh.badRequest()

        if(command.countIsNotValid())
            Oh.badRequest()

        inventoryOutPort.out(command)

        newInventoryLogPort.new(command)
    }
}
