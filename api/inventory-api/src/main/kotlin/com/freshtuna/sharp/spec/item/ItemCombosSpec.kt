package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID

interface ItemCombosSpec {

    fun combos(id: String, sellerID: SharpID): BasicResponse
}
