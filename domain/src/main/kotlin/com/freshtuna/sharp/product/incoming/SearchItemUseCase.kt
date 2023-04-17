package com.freshtuna.sharp.product.incoming

import com.freshtuna.sharp.product.ItemInfo
import com.freshtuna.sharp.product.request.SearchItemCommand

interface SearchItemUseCase {

    fun search(command: SearchItemCommand): List<ItemInfo>
}
