package com.freshtuna.sharp.response

import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "SKU 검색 결과")
class SKUSearchResponse(

    @Schema(description = "SKU ID")
    val id: String,

    @Schema(description = "판매자 ID")
    val sellerId: String,

    val name: String,

    @Schema(description = "가격정보")
    val price: Price,

    val barcode: String,

    val description: String,

    @Schema(description = "스펙(무게, 너비, 높이...)")
    val spec: Spec,

    @Schema(description = "현재 재고량")
    val count: Long
) {
}

fun SKU.toSearchResponse() = SKUSearchResponse(
    id.toString(),
    sellerId.toString(),
    name,
    price,
    barcode,
    description,
    spec,
    count
)
