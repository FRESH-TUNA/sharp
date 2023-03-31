package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.command.StockInCommand
import com.freshtuna.sharp.inventory.incoming.StockInUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import com.freshtuna.sharp.oh.Oh
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StockInService(
    private val stockInPort: StockInPort,
    private val findSkuPort: FindSkuPort
) : StockInUseCase {

    private val logger = KotlinLogging.logger{}

    override fun stockIn(command: StockInCommand) {
        val sku = findSkuPort.find(command.skuId)

        if(!sku.checkSameSeller(command.sellerId))
            Oh.badRequest()

        stockInPort.stockIn(command)
    }
}
