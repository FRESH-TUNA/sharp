package com.freshtuna.sharp.request.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.request.SkuRequest

class NewItemRequest(
    val name: String,

    val category: SharpCategory,

    val description: String,

    val sku: SkuRequest,

    private val composites: List<NewItemCompositePolicyRequest>,
) {

    fun toCommand(): ItemCommand {
        val composites = composites.map { composite -> composite.toCommand() }.toList()
        return ItemCommand(name, category, description, sku.toCommand(), composites)
    }
}
