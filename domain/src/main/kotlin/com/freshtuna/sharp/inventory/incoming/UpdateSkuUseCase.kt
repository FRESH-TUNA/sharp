package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.inventory.result.UpdateSkuResult

interface UpdateSkuUseCase {

    fun update(command: UpdateSkuCommand): UpdateSkuResult
}
