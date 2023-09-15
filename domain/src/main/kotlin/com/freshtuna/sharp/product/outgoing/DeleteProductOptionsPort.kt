package com.freshtuna.sharp.product.outgoing

import com.freshtuna.sharp.id.SharpID

interface DeleteProductOptionsPort {

    fun ofProduct(productID: SharpID)
}
