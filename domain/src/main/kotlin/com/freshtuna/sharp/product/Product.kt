package com.freshtuna.sharp.product

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.id.SharpID

class Product(
    var id: SharpID? = null,
    val sellerId: SharpID,

    val name: String,
    val category: SharpCategory,
    val description: String,

    val isSingle: Boolean,
    val optionNames: List<String>,
    val options: List<ProductOption>
)
