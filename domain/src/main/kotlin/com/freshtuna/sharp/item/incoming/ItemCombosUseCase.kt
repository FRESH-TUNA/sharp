package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemComboDetail

interface ItemCombosUseCase {

    fun combos(itemID: SharpID, sellerID: SharpID): List<ItemComboDetail>
}
