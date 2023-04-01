package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.request.NewSkuRequest
interface NewSkuSpec {

    fun new(request: NewSkuRequest): BasicResponse
}
