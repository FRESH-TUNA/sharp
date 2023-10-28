package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID

class ItemCombo(
    val id: SharpID,
    val itemId: SharpID,
    val amount: Long
) {

    fun toDetail(item: ItemSummary): ItemComboDetail {
        return ItemComboDetail(
            id = id,
            item = item,
            amount = amount
        )
    }
}