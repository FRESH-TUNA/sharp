package com.freshtuna.sharp.product.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.Product

interface FindProductPort {

    fun find(id: SharpID): Product
}
