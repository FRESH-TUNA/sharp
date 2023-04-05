package com.freshtuna.sharp.spec

import com.freshtuna.sharp.request.SkuStockInRequest
import com.freshtuna.sharp.api.response.BasicResponse

interface SKUStockInSpec {

    fun stockIn(request: SkuStockInRequest, id: Long): BasicResponse
}
