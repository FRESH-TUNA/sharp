package com.freshtuna.sharp.spec

import com.freshtuna.sharp.request.StockInRequest
import com.freshtuna.tooth.api.response.BasicResponse

interface StockInSpec {

    fun stockIn(request: StockInRequest): BasicResponse
}
