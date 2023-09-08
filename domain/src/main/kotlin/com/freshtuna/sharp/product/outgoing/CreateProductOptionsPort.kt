package com.freshtuna.sharp.product.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductOptionCommand

interface CreateProductOptionsPort {

    fun create(
        productID: SharpID,
        commands: List<NewProductOptionCommand>
    )
}
