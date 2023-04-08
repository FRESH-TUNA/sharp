package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SKUStockInCommand

interface StockInUseCase {

    fun stockIn(command: SKUStockInCommand, sellerId: PublicId)
}
