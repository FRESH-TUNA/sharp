package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU

interface FindSkuPort {

    fun find(skuId: PublicId): SKU
}
