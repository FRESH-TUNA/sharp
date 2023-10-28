package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.request.sku.InventoryRequest
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface InventoryInSpec {

    fun new(request: InventoryRequest, id: Long, sellerId: SharpID): BasicResponse
}
