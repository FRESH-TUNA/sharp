package com.freshtuna.sharp.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.id.SharpID

class ItemSummary(
    val id: SharpID,

    val name: String,

    val category: SharpCategory,

    val sellerId: SharpID,

    val skuId: SharpID,

    val isCombo: Boolean,

    val count: Long
)

