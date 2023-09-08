package com.freshtuna.sharp.product.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductOptionCommand

interface UpdateProductOptionsUseCase {

    fun update(productID: SharpID, sellerID: SharpID, command: List<NewProductOptionCommand>)
}

