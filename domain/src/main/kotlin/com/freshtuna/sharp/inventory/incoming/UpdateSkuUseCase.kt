package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.command.UpdateSkuCommand

interface UpdateSkuUseCase {

    fun update(command: SkuCommand, id: SharpID, sellerId: SharpID)
}
