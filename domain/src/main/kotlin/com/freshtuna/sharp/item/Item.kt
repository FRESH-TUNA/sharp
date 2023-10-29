package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.inventory.domain.SKU

class Item(

    var id: SharpID,

    val name: String,

    val category: SharpCategory,

    val sellerId: SharpID,

    val skuId: SharpID,

    val description: String,

    val isCombo: Boolean
) {

    fun toSummary(sku: SKU): ItemSummary {
        return ItemSummary(
            id = id,
            name = name,
            category = category,
            sellerId = sellerId,
            skuId = skuId,
            count = sku.count,
            isCombo = isCombo
        )
    }
}
