package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.StockInCommand

interface StockInPort {

    fun stockIn(command: StockInCommand)
}
