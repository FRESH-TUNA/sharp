package com.freshtuna.sharp.inventory.domain.inventory

import com.freshtuna.sharp.id.PublicId

class Inventory(
    val id: PublicId,
    val skuId: PublicId,
    val status: InventoryStatus,
    val condition: InventoryCondition
)
