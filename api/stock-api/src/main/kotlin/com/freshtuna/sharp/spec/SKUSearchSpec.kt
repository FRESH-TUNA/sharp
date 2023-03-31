package com.freshtuna.sharp.spec

import com.freshtuna.sharp.request.NewSkuRequest
import com.freshtuna.sharp.api.response.BasicResponse

interface NewSkuSpec {

    fun new(request: NewSkuRequest): BasicResponse
}
