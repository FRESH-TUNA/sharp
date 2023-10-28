package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.SkuCommand

interface NewSkuPort {

    fun new(command: SkuCommand, sellerID: SharpID) : SKU
}
