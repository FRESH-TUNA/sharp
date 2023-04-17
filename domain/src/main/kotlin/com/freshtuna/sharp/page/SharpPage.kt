package com.freshtuna.sharp.page

class SharpPage<T>(
    val page: List<T>,
    val count: Long,

    val pageNumber: Long,
    val pageSize: Long,

    val totalPageCount: Long,
    val totalCount: Long,
) {

    constructor(
        data: List<T>,
        totalCount: Long,
        pageRequest: SharpPageRequest
    ) : this(
        page = data,
        count = data.size.toLong(),

        pageNumber = pageRequest.pageNumber,
        pageSize = pageRequest.pageSize,

        totalPageCount = totalCount / pageRequest.pageSize,
        totalCount = totalCount,
    )
}
