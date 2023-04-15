package com.freshtuna.sharp.response

import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.domain.InventoryStatus

class InventoryResponse(
    val id: String,
    val skuId: String,
    val status: InventoryStatus,
    val count: Long
)

fun Inventory.toResponse() = InventoryResponse(
    id = id.stringId(),
    skuId = skuId.stringId(),
    status = status,
    count = count
)
