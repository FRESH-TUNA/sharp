package com.freshtuna.sharp.product.incoming

import com.freshtuna.sharp.product.command.StockInItemCommand

interface NewItemsUseCase {

    /**
     * 어떤 itemOption id를 넣을건지
     * 얼마나 넣을건지
     */
    fun stockIn(request: StockInItemCommand)
}
