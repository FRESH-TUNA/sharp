package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason

class InventoryCommand(

    val count: Long,

    val reason: InventoryLogReason,

    val description: String = ""
) {

    fun countIsNotValid() = count <= 0

    fun isIN() = reason.isIN()

    fun isOUT() = reason.isOUT()
}
