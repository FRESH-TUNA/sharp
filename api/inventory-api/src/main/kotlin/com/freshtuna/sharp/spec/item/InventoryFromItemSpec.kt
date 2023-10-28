package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.sku.InventoryRequest

interface InventoryFromItemSpec {

    fun from(request: InventoryRequest, id: Long, sellerId: SharpID): BasicResponse
}
