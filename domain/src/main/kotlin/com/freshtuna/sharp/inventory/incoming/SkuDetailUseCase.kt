package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.DetailSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU

interface SkuDetailUseCase {

    fun detail(command: DetailSkuCommand, sellerId: SharpID): SKU
}
