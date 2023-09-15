package com.freshtuna.sharp.product.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductCommand

interface NewProductUseCase {

    /**
     * 어떤 itemOption id를 넣을건지
     * 얼마나 넣을건지
     */
    fun new(command: NewProductCommand, sellerId: SharpID)
}
