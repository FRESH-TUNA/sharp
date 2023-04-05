package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.SKUStockInCommand

interface StockInPort {

    fun stockIn(command: SKUStockInCommand)
}
