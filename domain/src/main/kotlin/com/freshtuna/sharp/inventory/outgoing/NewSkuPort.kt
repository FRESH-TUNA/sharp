package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.NewSkuCommand

interface NewSkuPort {

    fun new(command: NewSkuCommand) : SKU
}
