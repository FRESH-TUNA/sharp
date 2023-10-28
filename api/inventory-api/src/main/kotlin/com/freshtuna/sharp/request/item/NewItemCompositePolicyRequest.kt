package com.freshtuna.sharp.request.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.ItemCompositePolicyCommand

class NewItemCompositePolicyRequest(
    private val itemId: String,
    private val amount: Long,
) {

    fun toCommand(): ItemCompositePolicyCommand {
        return ItemCompositePolicyCommand(SharpID(itemId), amount)
    }
}
