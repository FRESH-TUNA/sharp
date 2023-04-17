package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId

interface DeleteSkuPort {

    fun delete(id: PublicId)
}
