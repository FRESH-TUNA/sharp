package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.StockInCommand

interface StockInUseCase {

    fun stockIn(command: StockInCommand)
}
