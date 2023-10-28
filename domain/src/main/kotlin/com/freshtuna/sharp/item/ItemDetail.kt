package com.freshtuna.sharp.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU

class ItemDetail(
    val id: SharpID,

    val name: String,

    val sellerId: SharpID,

    val category: SharpCategory,

    val description: String,

    val sku: SKU,

    val combos: List<ItemComboDetail>
)  {

    constructor(
        item: Item,
        sku: SKU,
        composites: List<ItemComboDetail>
    ): this(
        id = item.id,
        name = item.name,
        sellerId = item.sellerId,
        category = item.category,
        description = item.description,
        sku,
        composites
    )
}
