package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.ItemCommand

interface UpdateItemPort {

    fun update(command: ItemCommand, id: SharpID)
}
