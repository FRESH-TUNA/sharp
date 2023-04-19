package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.page.SharpPage

interface SearchSkuPort {

    fun search(command: SearchSkuCommand, sellerId: SharpID): SharpPage<SKU>
}
