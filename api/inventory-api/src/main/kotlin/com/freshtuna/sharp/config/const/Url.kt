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

            const val IN_REASONS = "$ROOT/in/reasons"

            const val OUT_REASONS = "$ROOT/out/reasons"


            /**
             * sku
             */
            const val SKU = "$ROOT/sku"

            const val SKU_ID = "$ROOT/sku$ID"

            const val SKU_ID_INVENTORY_IN = "$ROOT/sku$ID/in"

            const val SKU_ID_INVENTORY_OUT = "$ROOT/sku$ID/out"

            const val SKU_ID_INVENTORY_LOGS = "$ROOT/sku$ID/logs"
        }
    }
}
