package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.InventoryOutRequest

interface InventoryOutSpec {

    fun new(request: InventoryOutRequest, id: Long, sellerId: SharpID): BasicResponse
}
