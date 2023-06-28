package com.freshtuna.sharp.seller

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID
import com.freshtuna.sharp.seller.incoming.SellerManageUseCase
import com.freshtuna.sharp.seller.outgoing.SellerManagePort
import org.springframework.stereotype.Service

@Service
class SellerManageService(
    private val sellerManagePort: SellerManagePort
) : SellerManageUseCase {

    override fun findOrCreateID(publicID: SharpPublicID): SharpID {
        return sellerManagePort.findIDBy(publicID) ?: return sellerManagePort.createID(publicID)
    }
}
