package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.page.SharpPageRequest

class SearchSkuCommand(
    val query: String = "",
    val sharpPageRequest: SharpPageRequest
)
