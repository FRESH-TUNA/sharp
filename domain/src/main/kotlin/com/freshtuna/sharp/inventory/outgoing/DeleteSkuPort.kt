package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID

interface DeleteSkuPort {

    fun delete(id: SharpID)
}
