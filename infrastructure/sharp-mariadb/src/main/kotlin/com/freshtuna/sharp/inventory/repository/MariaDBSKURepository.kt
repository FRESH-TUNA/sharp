package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import org.springframework.data.jpa.repository.JpaRepository

interface MariaDBSKURepository : JpaRepository<MariaDBSKU, Long>
