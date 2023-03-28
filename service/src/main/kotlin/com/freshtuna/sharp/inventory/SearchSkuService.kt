package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.inventory.incoming.SearchSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import org.springframework.stereotype.Service

@Service
class SearchSkuService(
    private val searchSkuPort: SearchSkuPort
) : SearchSkuUseCase {

    override fun search(command: SearchSkuCommand) = searchSkuPort.search(command)
}
