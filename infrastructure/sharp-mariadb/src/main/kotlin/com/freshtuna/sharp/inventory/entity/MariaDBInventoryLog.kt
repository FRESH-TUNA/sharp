package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogType
import jakarta.persistence.*

@Entity
@Table(name = "inventory_log")
class MariaDBInventoryLog(

    @ManyToOne
    val sku: MariaDBSKU,

    @Enumerated(EnumType.STRING)
    @Column(name = "`type`")
    val type: InventoryLogType,

    @Enumerated(EnumType.STRING)
    @Column(name = "`reason`")
    val reason: InventoryLogReason,

    @Column(name = "`count`")
    val count: Long,

    val description: String = ""

    ) : MariaDBDefaultEntity() {

    companion object {
        fun of(sku: MariaDBSKU, command: InventoryCommand) = MariaDBInventoryLog(
            sku = sku,
            type = if(command.reason.isIN()) InventoryLogType.INCOMING else InventoryLogType.OUTGOING,

            reason = command.reason,

            count = command.count,
            description = command.description
        )
    }

    fun toDomain() = InventoryLog(
        id = SharpID(id),
        skuId = SharpID(sku.id),

        type = type,
        reason = reason,

        count = count,
        description = description
    )
}
