package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse

interface SkuDetailSpec {

    fun detail(id: String): BasicResponse
}
