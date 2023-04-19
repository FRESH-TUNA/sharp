package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "재고 입/출고 요청")
class InventoryRequest(

    @Schema(description = "사유: 입고(NEW-신규, RETURN-반품), 출고(MODIFY-입고수정, OUT-배송)")
    @Pattern(regexp = "^(NEW|RETURN|MODIFY|SHIPPER)$")
    private val reason: InventoryLogReason,

    @Schema(description = "입/출고할 재고의 상태")
    @Pattern(regexp = "^(NEW|OLD)$")
    private val condition: InventoryCondition,

    @Schema(description = "수량")
    private val count: Long,

    @Schema(description = "비고")
    private val description: String,
) {
    fun toCommandOf(skuId: SharpID)
        = InventoryInOutCommand(
            skuId = skuId,
            count = count,
            reason = reason,
            condition = condition,
            description = description
        )
}
