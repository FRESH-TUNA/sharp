package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.UpdateSkuCommand
import com.freshtuna.sharp.inventory.result.SkuDetailResult

interface UpdateSkuUseCase {

    fun update(command: UpdateSkuCommand): SkuDetailResult
}
