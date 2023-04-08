package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId
import java.time.LocalDateTime

class SKUStockInCommand(

    val skuId: PublicId,
    val count: Long,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime
)
