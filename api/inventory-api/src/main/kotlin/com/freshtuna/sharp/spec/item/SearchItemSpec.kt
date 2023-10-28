package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.item.SearchItemRequest

interface SearchItemSpec {
    fun new(request: SearchItemRequest, sellerID: SharpID): BasicResponse
}
