package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.domain.InventoryStatus
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 입/출고 요청")
class InventoryRequest(
    @Schema(description = "사유: 입고(NEW-신규), 출고(MODIFY-입고수정, OUT-배송)")
    @Pattern(regexp = "^(NEW|MODIFY|OUT)$")
    private val status: InventoryStatus,

    @Schema(description = "수량")
    private val count: Long,
) {
    fun toCommand(skuId: PublicId)
        = NewInventoryCommand(
            skuId,
            count,
            status,
        )
}
