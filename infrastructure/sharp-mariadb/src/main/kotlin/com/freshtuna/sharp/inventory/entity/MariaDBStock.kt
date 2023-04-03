package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.inventory.command.StockInCommand
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "stock")
class MariaDBStock(

    @ManyToOne
    val sku: MariaDBSKU,

    val hasExpire: Boolean,
    val expireDate: LocalDateTime,

    val hasManufacture: Boolean,
    val manufactureDate: LocalDateTime

) : MariaDBDefaultEntity() {

    companion object {
        fun of(sku: MariaDBSKU, command: StockInCommand)
            = MariaDBStock(
                sku,
                command.hasExpire,
                command.expireDate,
                command.hasManufacture,
                command.manufactureDate
            )
    }
}

