package com.freshtuna.sharp.api.response;

/**
 * for page response
 */
class PageResponse<T> (
    val elements: List<T>,
    val page: PageInfo
)
