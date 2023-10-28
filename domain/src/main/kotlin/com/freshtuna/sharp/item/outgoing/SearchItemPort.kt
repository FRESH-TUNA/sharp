package com.freshtuna.sharp.item.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item

import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.page.SharpPage

interface SearchItemPort {

    fun search(command: SearchItemCommand, sellerId: SharpID): SharpPage<Item>
}
