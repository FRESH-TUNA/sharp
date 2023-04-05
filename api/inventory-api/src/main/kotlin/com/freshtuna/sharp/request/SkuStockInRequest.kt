package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import java.time.LocalDateTime

class SkuStockInRequest(
    private val count: Long,

    private val hasExpire: Boolean,
    private val expireDate: LocalDateTime?,

    private val hasManufacture: Boolean,
    private val manufactureDate: LocalDateTime?
) {
    fun toCommand(sellerId: PublicId, skuId: PublicId)
        = SKUStockInCommand(
            sellerId,
            skuId,
            count,
            hasExpire,
            if(hasExpire) expireDate!! else LocalDateTime.MIN,
            hasManufacture,
            if(hasManufacture) manufactureDate!! else LocalDateTime.MIN
        )
}
