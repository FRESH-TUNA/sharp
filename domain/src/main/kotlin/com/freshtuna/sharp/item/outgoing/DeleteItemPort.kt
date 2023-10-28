package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID

interface DeleteItemPort {

    fun delete(id: SharpID)
}
