package com.freshtuna.sharp.response.sku

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 입/출고 정보")
class InventoryResponse(
    val id: String,
    val skuId: String,

    @Schema(description = "사유: 입고(NEW-신규), 출고(MODIFY-입고수정, OUT-배송)")
    @Pattern(regexp = "^(NEW|MODIFY|OUT)$")
    val reason: InventoryLogReason,

    @Schema(description = "입/출고 한 재고의 갯수")
    val count: Long,

    val description: String
)

fun InventoryLog.toResponse() = InventoryResponse(
    id = id.stringId(),
    skuId = skuId.stringId(),
    reason = reason,
    count = count,
    description = description
)
