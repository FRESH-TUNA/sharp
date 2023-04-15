package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.domain.InventoryStatus

class InventoryRequest(
    private val status: InventoryStatus,
    private val count: Long,
) {
    fun toCommand(skuId: PublicId)
        = NewInventoryCommand(
            skuId,
            count,
            status,
        )
}
