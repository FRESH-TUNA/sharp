package com.freshtuna.sharp.seller.adapter

import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID
import com.freshtuna.sharp.seller.outgoing.SellerManagePort
import org.springframework.stereotype.Component

@Component
class SellerManageAdapter(
    private val sellerRepository: SellerRepository
) : SellerManagePort{

    override fun findIDBy(publicID: SharpPublicID): SharpID? {

        val seller = sellerRepository.findByPublicId(publicID.uuid()) ?: return null
        return SharpID(seller.id)
    }

    override fun createID(publicID: SharpPublicID): SharpID {

        val seller = sellerRepository.save(MariaDBSeller(publicID.uuid()))
        return SharpID(seller.id)
    }
}
