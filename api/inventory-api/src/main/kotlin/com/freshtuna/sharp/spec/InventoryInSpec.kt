package com.freshtuna.sharp.spec

import com.freshtuna.sharp.request.InventoryRequest
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface InventoryInSpec {

    fun new(request: InventoryRequest, id: Long, sellerId: SharpID): BasicResponse
}
