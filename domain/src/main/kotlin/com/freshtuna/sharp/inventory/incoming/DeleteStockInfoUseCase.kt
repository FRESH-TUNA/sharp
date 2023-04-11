package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId

interface DeleteStockInfoUseCase {

    fun delete(infoId: PublicId, sellerId: PublicId)
}
