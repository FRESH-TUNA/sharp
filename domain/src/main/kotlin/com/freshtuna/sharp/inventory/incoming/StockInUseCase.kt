package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.SKUStockInCommand

interface StockInUseCase {

    fun stockIn(command: SKUStockInCommand)
}
