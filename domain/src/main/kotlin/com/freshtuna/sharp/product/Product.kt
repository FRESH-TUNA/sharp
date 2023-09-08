package com.freshtuna.sharp.product

import com.freshtuna.sharp.id.SharpID

class Product(
    val name: String,
    val sellerID: SharpID
) {
    fun isOwner(sellerID: SharpID) = this.sellerID == sellerID
}
