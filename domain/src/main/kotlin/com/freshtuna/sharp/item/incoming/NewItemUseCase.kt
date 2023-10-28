package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.command.ItemCommand

interface NewItemUseCase {

    fun new(command: ItemCommand, sellerID: SharpID): Item
}
