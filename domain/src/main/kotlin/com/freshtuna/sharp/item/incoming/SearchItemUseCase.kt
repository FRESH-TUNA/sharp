package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemSummary
import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.page.SharpPage

interface SearchItemUseCase {

    fun search(command: SearchItemCommand, sellerId: SharpID): SharpPage<ItemSummary>
}
