package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse

interface DeleteSkuSpec {

    fun delete(id: String): BasicResponse
}
