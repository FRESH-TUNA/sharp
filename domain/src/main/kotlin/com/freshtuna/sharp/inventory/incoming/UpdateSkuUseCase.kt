package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.UpdateSkuCommand

interface UpdateSkuUseCase {

    fun update(command: UpdateSkuCommand)
}
