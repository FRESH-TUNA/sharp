package com.freshtuna.sharp.inventory.domain.inventory

/**
 * 재고의 상황
 */
enum class InventoryStatus {

    // 잔여상태, 상품 구성 가능
    READY,

    // 상품으로 구성됨
    PACKAGED,

    // 장바구니
    CART,

    ORDER,

    ORDER_COMPLETE
}
