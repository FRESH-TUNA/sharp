package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.result.SkuDetailResult

interface NewSkuUseCase {

    fun new(command: NewSkuCommand): SkuDetailResult
}
