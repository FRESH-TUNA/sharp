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

            private const val ROOT = ""

            /**
             * sku
             */
            const val SKU = "$ROOT/skus"

            const val SKU_ID = "$ROOT/skus$ID"

            const val SKU_ID_INVENTORY_IN = "$ROOT/skus$ID/in"

            const val SKU_ID_INVENTORY_OUT = "$ROOT/skus$ID/out"

            const val SKU_ID_INVENTORY_LOGS = "$ROOT/skus$ID/logs"


            const val IN_REASONS = "$SKU/in/reasons"

            const val OUT_REASONS = "$SKU/out/reasons"


            /**
             * item
             */
            const val ITEM = "$ROOT/items"

            const val ITEM_ID = "$ITEM$ID"

            const val ITEM_IN = "$ITEM_ID/in"

            const val ITEM_OUT = "$ITEM_ID/out"

            const val ITEM_COMBOS = "$ITEM_ID/combos"
        }
    }
}
