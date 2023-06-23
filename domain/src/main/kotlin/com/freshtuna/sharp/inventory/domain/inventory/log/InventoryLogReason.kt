package com.freshtuna.sharp.inventory.domain.inventory.log

/**
 * 입출고 사유
 */
enum class InventoryLogReason(
    val type: InventoryLogType
) {
    /**
     * 입고
     */
    // 신규
    NEW(InventoryLogType.INCOMING),

    // 반품
    RETURN(InventoryLogType.INCOMING),

    /**
     * 출고
     */
    // 입고 수정
    MODIFY(InventoryLogType.OUTGOING),

    // 배송
    SHIPPED(InventoryLogType.OUTGOING);

    companion object {
        val IN_REASONS = listOf(NEW, RETURN)
        val OUT_REASONS = listOf(MODIFY, SHIPPED)
    }

    fun isIN() = type == InventoryLogType.INCOMING

    fun isOUT() = type == InventoryLogType.OUTGOING
}
