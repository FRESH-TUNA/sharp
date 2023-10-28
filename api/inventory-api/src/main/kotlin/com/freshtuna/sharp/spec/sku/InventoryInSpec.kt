package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.request.InventoryInRequest
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface InventoryInSpec {

    fun new(request: InventoryInRequest, id: Long, sellerId: SharpID): BasicResponse
}
