package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity

import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import jakarta.persistence.*

@Entity
@Table(name = "inventory")
class MariaDBInventory(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`")
    val status: InventoryStatus,

    @Enumerated(EnumType.STRING)
    @Column(name = "`condition`")
    val condition: InventoryCondition
) : MariaDBDefaultEntity()

