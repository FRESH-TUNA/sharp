package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.StockInCommand
import java.time.LocalDateTime

class StockInRequest(
    private val skuId: String,
    private val count: Long,

    private val hasExpire: Boolean,
    private val expireDate: LocalDateTime?,

    private val hasManufacture: Boolean,
    private val manufactureDate: LocalDateTime?
) {
    fun toCommand(sellerId: PublicId)
        = StockInCommand(
            sellerId,
            PublicId(skuId),
            count,
            hasExpire,
            if(hasExpire) expireDate!! else LocalDateTime.MIN,
            hasManufacture,
            if(hasManufacture) manufactureDate!! else LocalDateTime.MIN
        )
}
