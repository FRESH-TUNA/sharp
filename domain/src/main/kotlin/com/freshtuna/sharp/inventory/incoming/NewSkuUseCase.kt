package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU

interface NewSkuUseCase {

    fun new(command: NewSkuCommand): SKU
}
