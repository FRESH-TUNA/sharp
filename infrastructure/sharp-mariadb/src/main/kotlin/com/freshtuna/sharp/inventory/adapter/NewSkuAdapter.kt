package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.inventory.entity.toEntity
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.inventory.repository.MariaDBSKURepository
import org.springframework.stereotype.Component

@Component
class NewSkuAdapter(
    private val repository: MariaDBSKURepository
) : NewSkuPort {

    override fun new(command: NewSkuCommand)
        = repository.save(command.toEntity()).toDomain()

}
