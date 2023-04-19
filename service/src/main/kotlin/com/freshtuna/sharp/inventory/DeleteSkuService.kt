package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.incoming.DeleteSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.oh.Oh
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteSkuService(
    private val findSkuPort: FindSkuPort,
    private val skuPort: DeleteSkuPort
) : DeleteSkuUseCase{

    override fun delete(command: DeleteSkuCommand, sellerId: SharpID) {
        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        skuPort.delete(sku.id)
    }
}
