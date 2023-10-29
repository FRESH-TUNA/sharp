package com.freshtuna.sharp.item.outgoing.combo

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo

interface SearchItemComboPort {

    fun search(itemId: SharpID): List<ItemCombo>
}
