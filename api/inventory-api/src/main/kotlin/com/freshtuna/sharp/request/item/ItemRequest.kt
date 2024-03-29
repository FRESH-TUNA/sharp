package com.freshtuna.sharp.request.item

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.request.sku.SkuRequest
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "아이템 생성/수정 요청")
class ItemRequest(

    @Schema(description = "이름")
    val name: String,

    @Schema(description = "카테고리")
    val category: SharpCategory,

    @Schema(description = "설명")
    val description: String = "",

    @Schema(description = "SKU 정보 작성")
    val sku: SkuRequest,

    @Schema(description = "세트 아이템인 경우 세트에 해당하는 아이템 추가")
    private val combos: List<NewItemCompositePolicyRequest> = emptyList(),
) {

    fun toCommand(): ItemCommand {
        val combos = combos.map { combo -> combo.toCommand() }.toList()
        return ItemCommand(name, category, description, sku.toCommand(), combos.isNotEmpty(), combos)
    }
}
