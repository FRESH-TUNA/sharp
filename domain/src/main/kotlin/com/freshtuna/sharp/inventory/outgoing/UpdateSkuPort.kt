package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand

interface UpdateSkuPort {

    fun update(command: UpdateSkuCommand) : SKU
}
