package com.freshtuna.sharp.inventory.domain

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.result.NewSkuResult
import com.freshtuna.sharp.inventory.result.SkuDetailResult
import com.freshtuna.sharp.inventory.result.SkuSearchResult

import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import java.time.LocalDateTime

class SKU(
    val id: PublicId,
    val sellerId: PublicId,

    val name: String,
    val price: Price,

    val barcode: String,
    val description: String,

    val expireDate: LocalDateTime,
    val manufactureDate: LocalDateTime,

    /**
     * specific
     */
    val spec: Spec,
    val count: Long = 0,
) {

    fun checkSameSeller(id: PublicId)
        = sellerId == id
    fun toResult() = SkuDetailResult(
        id.toString(),
        sellerId.toString(),
        name,
        price,
        barcode,
        description,
        spec,
        count
    )

    fun toNewResult() = NewSkuResult(id.toString())

    fun toSearchResult() = SkuSearchResult(
        id.toString(),
        sellerId.toString(),
        name,
        price,
        barcode,
        description,
        spec,
        count
    )
}
