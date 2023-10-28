package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.SkuRequest

interface UpdateSkuSpec {

    fun update(request: SkuRequest, id: String, sellerId: SharpID): BasicResponse
}
