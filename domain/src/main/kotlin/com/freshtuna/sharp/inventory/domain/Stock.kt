package com.freshtuna.sharp.inventory.domain

import com.freshtuna.sharp.id.PublicId

class Stock(
    val id: PublicId,
    val skuId: PublicId,
    val infoId: PublicId,

    val status: StockStatus
)