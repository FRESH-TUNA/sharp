package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.SearchSkuCommand

interface SearchSkuUseCase {

    fun search(command: SearchSkuCommand): List<SKU>
}
