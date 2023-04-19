package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU

interface FindSkuPort {

    fun find(skuId: SharpID): SKU
}
