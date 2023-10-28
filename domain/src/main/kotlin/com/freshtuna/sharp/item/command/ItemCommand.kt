package com.freshtuna.sharp.item.command

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.inventory.command.SkuCommand

class ItemCommand (

    val name: String,

    val category: SharpCategory,

    val description: String,

    val sku: SkuCommand,

    val composites: List<ItemCompositePolicyCommand>
) {

}
