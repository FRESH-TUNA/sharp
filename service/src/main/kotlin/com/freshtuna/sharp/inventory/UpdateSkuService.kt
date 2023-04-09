package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.inventory.incoming.UpdateSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.UpdateSkuPort

import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateSkuService(
    private val updateSkuPort: UpdateSkuPort,
    private val findSkuPort: FindSkuPort
) : UpdateSkuUseCase{

    override fun update(command: UpdateSkuCommand, sellerId: PublicId) {

        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        updateSkuPort.update(command)
    }
}
