package com.freshtuna.sharp.response.item

import com.freshtuna.sharp.item.ItemComboDetail
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "세트 아이템 구성항목")
class ItemComboDetailResponse(

    @Schema(description = "아이디")
    val id: String,

    @Schema(description = "세트 구성 아이템 요약 정보")
    val item: ItemResponse,

    @Schema(description = "적용 수량")
    val amount: Long
) {
}

fun ItemComboDetail.toResponse(): ItemComboDetailResponse {

    return ItemComboDetailResponse(
        id = id.stringId(),
        item = item.toResponse(),
        amount = amount
    )
}
