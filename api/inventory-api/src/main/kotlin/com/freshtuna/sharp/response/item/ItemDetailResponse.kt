package com.freshtuna.sharp.response.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.item.ItemComboDetail

import com.freshtuna.sharp.item.ItemDetail
import com.freshtuna.sharp.response.sku.SKUResponse
import com.freshtuna.sharp.response.sku.toResponse
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "아이템 상세 정보")
class ItemDetailResponse(

    @Schema(description = "아이디")
    val id: String,

    @Schema(description = "이름")
    val name: String,

    @Schema(description = "카테고리")
    val category: SharpCategory,

    @Schema(description = "설명")
    val description: String,

    @Schema(description = "SKU, 재고 관리 정보")
    val sku: SKUResponse,

    @Schema(description = "콤보 상품 여부")
    val isCombo: Boolean,
)

fun ItemDetail.toResponse(): ItemDetailResponse {

    return ItemDetailResponse(
        id = id.stringId(),
        name = name,
        category = category,
        description = description,
        sku = sku.toResponse(),
        isCombo = isCombo
    )
}
