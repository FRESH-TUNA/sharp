package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.inventory.StockStatus
import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stock")
class MariaDBStock(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    val status: StockStatus,

    @ManyToOne
    val info: MariaDBStockInfo,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,

    ) : MariaDBDefaultEntity() {

    companion object {
        fun newStock(sku: MariaDBSKU, info: MariaDBStockInfo, command: SKUStockInCommand)
            = MariaDBStock(
                sku,
                StockStatus.AVAILABLE,
                info,
                command.hasExpire,
                command.expireDate,
                command.hasManufacture,
                command.manufactureDate
            )
    }
}
