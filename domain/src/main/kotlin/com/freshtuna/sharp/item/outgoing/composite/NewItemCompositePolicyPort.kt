package com.freshtuna.sharp.item.outgoing.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCompositePolicy
import com.freshtuna.sharp.item.command.ItemCompositePolicyCommand

interface NewItemCompositePolicyPort {
    fun new(command: List<ItemCompositePolicyCommand>, id: SharpID): List<ItemCompositePolicy>
}
