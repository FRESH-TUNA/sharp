package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.inventory.result.NewSkuResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewSkuService(
    private val newSkuPort: NewSkuPort
) : NewSkuUseCase {

    override fun new(command: NewSkuCommand) = newSkuPort.new(command).toNewResult()
}
