package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.result.StockInfoResult
import java.time.LocalDateTime

class StockInfo(
    val id: PublicId,
    val skuId: PublicId,
    val requestStatus: StockRequestStatus,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,

    val remainCount: Long,
    val totalCount: Long,
) {

    fun toResult() = StockInfoResult(
        id = id.stringId(),
        skuId = skuId.stringId(),

        requestStatus = requestStatus,

        hasExpire = hasExpire,
        expireDate = expireDate,

        hasManufacture = hasManufacture,
        manufactureDate = manufactureDate,

        remainCount = remainCount,
        totalCount = totalCount
    )
}
