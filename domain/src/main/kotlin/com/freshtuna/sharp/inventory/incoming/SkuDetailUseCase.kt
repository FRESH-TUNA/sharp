package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU

interface SkuDetailUseCase {

    fun detail(id: SharpID, sellerId: SharpID): SKU
}
