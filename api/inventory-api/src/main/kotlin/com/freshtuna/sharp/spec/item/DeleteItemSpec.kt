package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface DeleteItemSpec {

    fun update(id: String, sellerId: SharpID): BasicResponse
}