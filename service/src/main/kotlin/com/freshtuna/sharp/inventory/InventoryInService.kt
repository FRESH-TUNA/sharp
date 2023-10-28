package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.oh.Oh
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryInService(
    private val newInventoryLogPort: NewInventoryLogPort,
    private val inventoryInPort: InventoryInPort,
    private val findSkuPort: FindSkuPort
) : InventoryInUseCase {

    private val logger = KotlinLogging.logger{}

    override fun new(command: InventoryCommand, skuId: SharpID, sellerId: SharpID) {

        val sku = findSkuPort.find(skuId)

        if(!command.isIN())
            Oh.badRequest()

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(command.countIsNotValid())
            Oh.badRequest()

        newInventoryLogPort.new(command, skuId)

        inventoryInPort.`in`(command, skuId)
    }
}
