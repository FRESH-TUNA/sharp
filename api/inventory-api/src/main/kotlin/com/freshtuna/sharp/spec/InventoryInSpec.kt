package com.freshtuna.sharp.spec

import com.freshtuna.sharp.request.InventoryRequest
import com.freshtuna.sharp.api.response.BasicResponse

interface InventoryInSpec {

    fun new(request: InventoryRequest, id: Long): BasicResponse
}
