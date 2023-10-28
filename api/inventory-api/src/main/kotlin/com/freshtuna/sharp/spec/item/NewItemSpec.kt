package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.item.NewItemRequest

interface NewItemSpec {

    fun new(request: NewItemRequest, sellerID: SharpID): BasicResponse
}
