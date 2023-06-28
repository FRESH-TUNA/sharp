package com.freshtuna.sharp.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*
@Entity
@Table(name = "seller")
class MariaDBSeller(
    @Column
    var publicId: UUID
) : MariaDBDefaultEntity()
