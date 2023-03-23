package com.freshtuna.sharp.page

data class Page(
    val pageNumber: Int,
    val pageSize: Int,
    val sort: Sort,
)