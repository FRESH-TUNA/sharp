package com.freshtuna.sharp.response

import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.domain.InventoryStatus
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 입/출고 정보")
class InventoryResponse(
    val id: String,
    val skuId: String,

    @Schema(description = "사유: 입고(NEW-신규), 출고(MODIFY-입고수정, OUT-배송)")
    @Pattern(regexp = "^(NEW|MODIFY|OUT)$")
    val status: InventoryStatus,

    @Schema(description = "입/출고 한 재고의 갯수")
    val count: Long
)

fun Inventory.toResponse() = InventoryResponse(
    id = id.stringId(),
    skuId = skuId.stringId(),
    status = status,
    count = count
)
