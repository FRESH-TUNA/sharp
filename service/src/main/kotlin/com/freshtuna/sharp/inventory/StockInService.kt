package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.command.StockInCommand
import com.freshtuna.sharp.inventory.incoming.StockInUseCase
import com.freshtuna.sharp.inventory.outgoing.StockInPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StockInService(
    private val stockInPort: StockInPort
) : StockInUseCase {

    override fun stockIn(command: StockInCommand) {
        stockInPort.stockIn(command)
    }
}
