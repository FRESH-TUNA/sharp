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

    val availableCount: Long,
    val totalCount: Long,
) {

    companion object {

        fun of(stockInfo: StockInfo, availableCount: Long, totalCount: Long)
            = StockInfoResult(
                id = stockInfo.id.stringId(),
                skuId = stockInfo.skuId.stringId(),

                requestStatus = stockInfo.requestStatus,

                hasExpire = stockInfo.hasExpire,
                expireDate = stockInfo.expireDate,

                hasManufacture = stockInfo.hasManufacture,
                manufactureDate = stockInfo.manufactureDate,

                availableCount = availableCount,
                totalCount = totalCount
            )
    }
}
