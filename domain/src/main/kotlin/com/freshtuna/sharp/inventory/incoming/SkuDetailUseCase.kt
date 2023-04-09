package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.DetailSkuCommand
import com.freshtuna.sharp.inventory.result.SkuDetailResult

interface SkuDetailUseCase {

    fun detail(command: DetailSkuCommand, sellerId: PublicId): SkuDetailResult
}
