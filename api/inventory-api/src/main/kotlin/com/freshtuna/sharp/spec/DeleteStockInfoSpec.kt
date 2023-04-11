package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse

interface DeleteStockInfoSpec {

    fun delete(id: String): BasicResponse
}
