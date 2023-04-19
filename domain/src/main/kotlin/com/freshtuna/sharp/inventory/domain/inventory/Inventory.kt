package com.freshtuna.sharp.inventory.domain.inventory

import com.freshtuna.sharp.id.SharpID

class Inventory(
    val id: SharpID,
    val skuId: SharpID,
    val status: InventoryStatus,
    val condition: InventoryCondition
)
