package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemSummary

interface ItemListUseCase {

    fun findAllByIds(itemIds: List<SharpID>, sellerID: SharpID): List<ItemSummary>
}
