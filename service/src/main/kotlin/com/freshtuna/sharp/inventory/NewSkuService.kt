package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewSkuService(
    private val newSkuPort: NewSkuPort
) : NewSkuUseCase {

    override fun new(command: SkuCommand, sellerId: SharpID) = newSkuPort.new(command, sellerId)
}
