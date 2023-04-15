package com.freshtuna.sharp.inventory.result

import com.freshtuna.sharp.inventory.domain.InventoryStatus
import java.time.LocalDateTime

class StockInfoResult(
    val id: String,
    val skuId: String,
    val requestStatus: InventoryStatus,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,

    val remainCount: Long,
    val totalCount: Long,
)
