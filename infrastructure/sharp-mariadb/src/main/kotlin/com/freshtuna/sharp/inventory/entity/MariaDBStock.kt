package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.Stock
import com.freshtuna.sharp.inventory.StockStatus
import com.freshtuna.sharp.inventory.command.SKUStockInCommand
import jakarta.persistence.*

@Entity
@Table(name = "stock")
class MariaDBStock(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    val status: StockStatus,

    @ManyToOne
    val info: MariaDBStockInfo,

    ) : MariaDBDefaultEntity() {

    companion object {
        fun newStock(sku: MariaDBSKU, info: MariaDBStockInfo, command: SKUStockInCommand)
            = MariaDBStock(
                sku,
                StockStatus.AVAILABLE,
                info
            )
    }

    fun toDomain() =
        Stock(PublicId(id), PublicId(sku.id), PublicId(info.id), status)
}
