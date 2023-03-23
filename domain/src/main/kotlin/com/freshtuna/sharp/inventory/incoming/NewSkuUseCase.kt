package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.NewSkuCommand

interface NewSkuUseCase {

    fun new(command: NewSkuCommand): SKU
}
