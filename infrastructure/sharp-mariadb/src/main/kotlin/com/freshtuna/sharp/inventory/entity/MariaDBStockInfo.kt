package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.StockInfo
import com.freshtuna.sharp.inventory.StockRequestStatus
import com.freshtuna.sharp.inventory.StockStatus
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

    @OneToMany(mappedBy = "info")
    private var _stocks: MutableList<MariaDBStock> = mutableListOf()

    val stocks: List<MariaDBStock>
        get() = _stocks.toList()

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

    fun toDomain(remainCount: Long, totalCount: Long): StockInfo{

        return StockInfo(
            id = PublicId(id),
            skuId = PublicId(sku.id),

            requestStatus = requestStatus,

            hasExpire = hasExpire,
            expireDate = expireDate,

            hasManufacture = hasManufacture,
            manufactureDate = manufactureDate,

            remainCount, totalCount
        )
    }

    fun toDomain(): StockInfo{

        return StockInfo(
            id = PublicId(id),
            skuId = PublicId(sku.id),

            requestStatus = requestStatus,

            hasExpire = hasExpire,
            expireDate = expireDate,

            hasManufacture = hasManufacture,
            manufactureDate = manufactureDate,

            remainCount = _stocks.filter { stock -> stock.status == StockStatus.AVAILABLE }.size.toLong(),
            totalCount = _stocks.size.toLong()
        )
    }
}


