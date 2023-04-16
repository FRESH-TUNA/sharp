package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.incoming.SearchSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort

import com.freshtuna.sharp.page.SharpPage
import org.springframework.stereotype.Service

@Service
class SearchSkuService(
    private val searchSkuPort: SearchSkuPort
) : SearchSkuUseCase {

    override fun search(command: SearchSkuCommand, sellerId: PublicId): SharpPage<SKU>{

        return searchSkuPort.search(command, sellerId)
    }
}
