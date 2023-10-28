package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item

interface ShowItemPort {
    fun show(id: SharpID): Item
}
