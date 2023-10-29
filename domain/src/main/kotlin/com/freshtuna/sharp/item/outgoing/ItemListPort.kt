package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item

interface ItemListPort {

    fun findAllByIds(ids: List<SharpID>): List<Item>
}
