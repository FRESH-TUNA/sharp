package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.result.NewSkuResult
import com.freshtuna.sharp.inventory.result.UpdateSkuResult
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec

class SKU(
    val id: PublicId,
    val sellerId: PublicId,

    val name: String,
    val price: Price,

    val barcode: String,

    val description: String,

    /**
     * specific
     */
    val spec: Spec
) {
    fun checkSameSeller(id: PublicId)
        = sellerId == id
    fun toResult() = NewSkuResult(id.toString())
    fun toUpdateSkuResult() = UpdateSkuResult(id)
}
