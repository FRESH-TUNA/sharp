package com.freshtuna.sharp.page

class SharpPage<T>(
    val data: List<T>,
    val count: Long,

    val pageNumber: Long,
    val pageCount: Long,

    val totalPageCount: Long,
    val totalCount: Long,
) {

    constructor(
        data: List<T>,
        totalSize: Long,
        pageRequest: SharpPageRequest
    ) : this(
        data = data,
        count = data.size.toLong(),

        pageNumber = pageRequest.pageNumber,
        pageCount = pageRequest.pageSize,

        totalPageCount = totalSize / pageRequest.pageSize,
        totalCount = totalSize,
    )
}
