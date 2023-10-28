package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.sku.SkuRequest

interface NewSkuSpec {

    fun new(request: SkuRequest, sellerId: SharpID): BasicResponse
}
