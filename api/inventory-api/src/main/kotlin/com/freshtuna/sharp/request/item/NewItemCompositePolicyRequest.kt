package com.freshtuna.sharp.request.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.ItemComboCommand

class NewItemCompositePolicyRequest(
    private val itemId: String,
    private val amount: Long,
) {

    fun toCommand(): ItemComboCommand {
        return ItemComboCommand(SharpID(itemId), amount)
    }
}
