package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface DeleteStockInfoSpec {

    fun delete(id: String, sellerId: SharpID): BasicResponse
}
