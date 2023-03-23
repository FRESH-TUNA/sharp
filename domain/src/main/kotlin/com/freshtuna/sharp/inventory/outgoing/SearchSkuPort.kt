package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.SearchSkuCommand

interface SearchSkuPort {

    fun search(command: SearchSkuCommand): List<SKU>
}
