package com.freshtuna.sharp.entity.repository

import com.freshtuna.sharp.entity.MariaDBSeller
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SellerRepository : JpaRepository<MariaDBSeller, Long> {

    fun findByPublicId(id: UUID): MariaDBSeller?
}
