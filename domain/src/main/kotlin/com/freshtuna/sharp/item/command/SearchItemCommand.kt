package com.freshtuna.sharp.item.command

import com.freshtuna.sharp.page.SharpPageRequest

class SearchItemCommand(
    val pageRequest: SharpPageRequest,
    val query: String
) {
}