package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.sku.InventoryOutRequest

interface InventoryOutSpec {

    fun new(request: InventoryOutRequest, id: Long, sellerId: SharpID): BasicResponse
}
