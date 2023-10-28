package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 출고 요청")
class InventoryOutRequest(

    @Schema(description = "사유: 출고(MODIFY-입고수정, OUT-배송)", example = "SHIPPED")
    @Pattern(regexp = "^(MODIFY|SHIPPED)$")
    private val reason: InventoryLogReason,

    @Schema(description = "수량")
    private val count: Long,

    @Schema(description = "비고")
    private val description: String
) {
    fun toCommand() = InventoryCommand(
        count = count,
        reason = reason,
        description = description
    )
}
