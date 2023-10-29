package com.freshtuna.sharp.item.outgoing.combo

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo
import com.freshtuna.sharp.item.command.ItemComboCommand

interface NewItemComboPort {
    fun new(command: List<ItemComboCommand>, id: SharpID): List<ItemCombo>
}
