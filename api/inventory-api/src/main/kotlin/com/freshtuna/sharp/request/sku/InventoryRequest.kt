package com.freshtuna.sharp.request.sku

import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 입고 요청")
class InventoryRequest(

    @Schema(description = "사유: 입고(NEW-신규, RETURN-반품)", example = "NEW")
    @Pattern(regexp = "^(NEW|RETURN)$")
    private val reason: InventoryLogReason,

    @Schema(description = "수량")
    private val count: Long,

    @Schema(description = "비고")
    private val description: String
) {
    fun toCommand()
            = InventoryCommand(
        count = count,
        reason = reason,
        description = description
    )
}
