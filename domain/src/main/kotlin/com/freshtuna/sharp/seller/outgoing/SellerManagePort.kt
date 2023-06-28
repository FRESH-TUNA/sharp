package com.freshtuna.sharp.seller.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID

interface SellerManagePort {

    fun findIDBy(publicID: SharpPublicID): SharpID?

    fun createID(publicID: SharpPublicID): SharpID
}
