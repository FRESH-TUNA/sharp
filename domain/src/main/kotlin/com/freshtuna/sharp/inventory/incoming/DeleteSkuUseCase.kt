package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.DeleteSkuCommand

interface DeleteSkuUseCase {

    fun delete(command: DeleteSkuCommand)
}
