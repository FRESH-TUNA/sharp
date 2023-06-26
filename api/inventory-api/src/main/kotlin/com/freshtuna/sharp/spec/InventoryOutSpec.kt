package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.InventoryRequest

interface InventoryOutSpec {

    fun new(request: InventoryRequest, id: Long, sellerId: SharpID): BasicResponse
}
