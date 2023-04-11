package com.freshtuna.sharp.inventory.result

import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.StockRequestStatus
import java.time.LocalDateTime

class StockInfoResult(
    val id: String,
    val skuId: String,
    val requestStatus: StockRequestStatus,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,

    val remainCount: Long,
    val totalCount: Long,
)

