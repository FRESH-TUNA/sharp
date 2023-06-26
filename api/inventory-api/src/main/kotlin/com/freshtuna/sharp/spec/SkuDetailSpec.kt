package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface SkuDetailSpec {

    fun detail(id: String, sellerId: SharpID): BasicResponse
}
