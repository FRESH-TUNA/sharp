package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.NewProductRequest

interface NewProductSpec {

    fun new(request: NewProductRequest, sellerId: SharpID): BasicResponse
}
