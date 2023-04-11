package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import java.time.LocalDateTime

class StockInfo(
    val id: PublicId,
    val skuId: PublicId,
    val requestStatus: StockRequestStatus,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime
)
