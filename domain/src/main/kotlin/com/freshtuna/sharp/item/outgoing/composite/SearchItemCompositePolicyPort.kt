package com.freshtuna.sharp.item.outgoing.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCompositePolicy

interface SearchItemCompositePolicyPort {

    fun search(itemId: SharpID): List<ItemCompositePolicy>
}
