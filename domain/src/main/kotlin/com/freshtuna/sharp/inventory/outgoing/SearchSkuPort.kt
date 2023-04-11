package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.page.SharpPage

interface SearchSkuPort {

    fun search(command: SearchSkuCommand, sellerId: PublicId): SharpPage<SKU>
}
