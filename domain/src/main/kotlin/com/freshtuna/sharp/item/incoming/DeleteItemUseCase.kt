package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID

interface DeleteItemUseCase {

    fun delete(id: SharpID, sellerID: SharpID)
}
