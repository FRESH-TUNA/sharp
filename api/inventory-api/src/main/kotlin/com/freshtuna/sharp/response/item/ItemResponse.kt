package com.freshtuna.sharp.response.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.item.ItemSummary
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "아이템 요약 정보")
class ItemResponse(

    @Schema(description = "아이디")
    val id: String,

    @Schema(description = "SKU 아이디")
    val skuId: String,

    @Schema(description = "이름")
    val name: String,

    @Schema(description = "카테고리")
    val category: SharpCategory,

    @Schema(description = "콤보 상품 여부")
    val isCombo: Boolean,

    @Schema(description = "재고 수량")
    val count: Long
) {
}

fun ItemSummary.toResponse(): ItemResponse {

    return ItemResponse(
        id = id.stringId(),
        skuId = skuId.stringId(),
        name = name,
        category = category,
        count = count,
        isCombo = isCombo
    )
}
