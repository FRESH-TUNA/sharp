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

            const val SKU_ID_STOCK = "$ROOT/sku$ID/stock"

            const val SKU_ID_STOCK_INFO = "$ROOT/sku$ID/stock-info"

            const val STOCK = "$ROOT/stock"
        }
    }
}
