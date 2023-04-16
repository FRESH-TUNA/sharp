package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.request.SkuRequest

interface NewSkuSpec {

    fun new(request: SkuRequest): BasicResponse
}
