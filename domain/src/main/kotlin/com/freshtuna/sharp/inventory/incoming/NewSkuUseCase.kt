package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.domain.SKU

interface NewSkuUseCase {

    fun new(command: SkuCommand, sellerId: SharpID): SKU
}
