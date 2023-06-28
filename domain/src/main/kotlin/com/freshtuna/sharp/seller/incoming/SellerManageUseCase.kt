package com.freshtuna.sharp.seller.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID

interface SellerManageUseCase {

    fun findOrCreateID(token: SharpPublicID): SharpID
}
