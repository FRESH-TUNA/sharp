package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.domain.InventoryStatus
import com.freshtuna.sharp.inventory.domain.StockStatus
import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "inventory")
class MariaDBInventory(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    val status: InventoryStatus,

    val count: Long,

    ) : MariaDBDefaultEntity() {

    companion object {
        fun of(sku: MariaDBSKU, command: NewInventoryCommand) = MariaDBInventory(
            sku = sku,
            status = command.status,
            count = command.count
        )
    }

    fun toDomain(): Inventory {

        return Inventory(
            id = PublicId(id),
            skuId = PublicId(sku.id),
            status = status,
            count = count
        )
    }
}


