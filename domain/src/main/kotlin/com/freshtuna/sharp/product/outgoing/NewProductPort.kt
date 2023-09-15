package com.freshtuna.sharp.product.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductCommand

interface NewProductPort {

    fun newProduct(command: NewProductCommand, sellerId: SharpID)
}
