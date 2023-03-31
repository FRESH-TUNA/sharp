package com.freshtuna.sharp.config.const

/**
 * 컨트롤러와 mapping 되는 url 설정
 */
class Url {

    /**
     * external api 설정
     */
    class EXTERNAL {

        companion object {

            private const val ROOT = "/inventory"

            /**
             * sku
             */
            const val SKU = "/inventory/sku"


            const val STOCK = "/inventory/stock"
        }
    }
}
