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

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: StockStatus

) : MariaDBDefaultEntity() {

    companion object {
        fun newStock(sku: MariaDBSKU, command: SKUStockInCommand)
            = MariaDBStock(
                sku,
                command.hasExpire,
                command.expireDate,
                command.hasManufacture,
                command.manufactureDate,
                StockStatus.AVAILABLE
            )
    }
}
