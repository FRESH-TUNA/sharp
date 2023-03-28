package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "stock")
class MariaDBStock(

    @ManyToOne
    val sku: MariaDBSKU,

) : MariaDBDefaultEntity()
