package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.command.ItemCommand

interface NewItemPort {

    fun new(item: ItemCommand, skuId: SharpID, sellerId: SharpID): Item
}
