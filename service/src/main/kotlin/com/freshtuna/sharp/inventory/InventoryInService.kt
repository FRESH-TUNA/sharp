package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryPort
import com.freshtuna.sharp.oh.Oh
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InventoryInService(
    private val newInventoryPort: NewInventoryPort,
    private val findSkuPort: FindSkuPort
) : InventoryInUseCase {

    private val logger = KotlinLogging.logger{}

    override fun new(command: NewInventoryCommand, sellerId: PublicId) {

        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        if(!command.status.isIN())
            Oh.badRequest()

        newInventoryPort.new(command)
    }
}
