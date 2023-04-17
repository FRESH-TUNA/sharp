package com.freshtuna.sharp.page

data class SharpPageRequest(
    val pageNumber: Long = 0,
    val pageSize: Long = 10,

    val sharpSort: SharpSort = SharpSort()
)