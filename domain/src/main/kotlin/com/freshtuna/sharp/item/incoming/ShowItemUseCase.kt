package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemDetail

interface ShowItemUseCase {
    fun show(itemID: SharpID, sellerId: SharpID): ItemDetail
}
