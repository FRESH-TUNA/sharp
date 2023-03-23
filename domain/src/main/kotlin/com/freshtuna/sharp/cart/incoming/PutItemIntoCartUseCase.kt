package com.freshtuna.sharp.cart.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.member.Member

interface PutItemIntoCartUseCase {

    fun put(member: Member, itemId: PublicId)
}
