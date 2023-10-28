package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.ItemCommand

interface UpdateItemUseCase {
    fun update(command: ItemCommand, itemId: SharpID, sellerId: SharpID)
}
