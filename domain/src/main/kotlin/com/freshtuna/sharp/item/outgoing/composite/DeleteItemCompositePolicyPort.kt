package com.freshtuna.sharp.item.outgoing.composite

import com.freshtuna.sharp.id.SharpID

interface DeleteItemCompositePolicyPort {

    fun deleteAllByItemId(id: SharpID)
}
