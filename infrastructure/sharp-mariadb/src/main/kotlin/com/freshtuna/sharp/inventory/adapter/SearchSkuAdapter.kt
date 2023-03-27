package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.repository.SKURepository
import org.springframework.stereotype.Component

@Component
class SearchSkuAdapter(
    private val repository: SKURepository
) : SearchSkuPort {

    override fun search(command: SearchSkuCommand): List<SKU> {
        TODO("Not yet implemented")
    }
}
