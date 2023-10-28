package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.domain.SKU

interface UpdateSkuPort {

    fun update(command: SkuCommand, id: SharpID) : SKU
}
