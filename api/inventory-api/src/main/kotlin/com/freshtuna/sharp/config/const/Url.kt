package com.freshtuna.sharp.config.const

/**
 * 컨트롤러와 mapping 되는 url 설정
 */
class Url {

    companion object {
        private const val ID = "/{id}"
    }

    /**
     * external api 설정
     */
    class EXTERNAL {

        companion object {

            private const val ROOT = "/inventory"

            /**
             * sku
             */
            const val SKU = "$ROOT/sku"

            const val SKU_ID = "$ROOT/sku$ID"

            const val SKU_ID_INVENTORY_IN = "$ROOT/sku$ID/inventories/in"

            const val SKU_ID_INVENTORY_OUT = "$ROOT/sku$ID/inventories/out"

            const val SKU_ID_INVENTORIES = "$ROOT/sku$ID/inventories"
        }
    }
}
