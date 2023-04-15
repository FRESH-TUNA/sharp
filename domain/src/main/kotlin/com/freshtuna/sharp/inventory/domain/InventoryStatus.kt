package com.freshtuna.sharp.inventory.domain

enum class InventoryStatus(
    private val IN: Boolean
) {
    /**
     * 입고
     */
    NEW(true),

    /**
     * 출고
     */
    MODIFY(false),
    OUT(false);

    fun isIN() = IN

    fun isOUT() = !IN
}
