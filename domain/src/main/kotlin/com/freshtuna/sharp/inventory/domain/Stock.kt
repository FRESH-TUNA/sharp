package com.freshtuna.sharp.inventory.domain

import com.freshtuna.sharp.id.SharpID

class Stock(
    val id: SharpID,
    val skuId: SharpID,
    val infoId: SharpID,

    val status: StockStatus
)
