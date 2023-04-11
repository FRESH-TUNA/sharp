package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.StockRequestStatus
import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stock_info")
class MariaDBStockInfo(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    val requestStatus: StockRequestStatus,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,
) : MariaDBDefaultEntity() {

    companion object {
        fun of(sku: MariaDBSKU, command: SKUStockInCommand, status: StockRequestStatus)
            = MariaDBStockInfo(
                sku = sku,
                requestStatus = status,

                hasManufacture = command.hasManufacture,
                hasExpire = command.hasExpire,

                expireDate = command.expireDate,
                manufactureDate = command.manufactureDate
            )
    }

    fun toDomain()
        = StockInfo(
            id = PublicId(id),
            skuId = PublicId(sku.id),

            requestStatus = requestStatus,

            hasExpire = hasExpire,
            expireDate = expireDate,

            hasManufacture = hasManufacture,
            manufactureDate = manufactureDate
        )
}
