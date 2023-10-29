package com.freshtuna.sharp.item.outgoing.combo

import com.freshtuna.sharp.id.SharpID

interface DeleteItemCompositePolicyPort {

    fun deleteAllByItemId(id: SharpID)
}
