package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.inventory.StockRequestStatus
import jakarta.persistence.*

@Entity
@Table(name = "stock_info")
class MariaDBStockInfo(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    val requestStatus: StockRequestStatus
) : MariaDBDefaultEntity()
