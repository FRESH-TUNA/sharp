package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.item.ItemRequest

interface NewItemSpec {

    fun new(request: ItemRequest, sellerID: SharpID): BasicResponse
}
