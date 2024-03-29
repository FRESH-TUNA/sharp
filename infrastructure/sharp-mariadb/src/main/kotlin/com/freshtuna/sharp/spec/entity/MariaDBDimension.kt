package com.freshtuna.sharp.spec.entity

import com.freshtuna.sharp.spec.Dimension
import com.freshtuna.sharp.spec.DimensionScale
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class MariaDBDimension(

    @Column(name = "width")
    val width: String,

    @Column(name = "height")
    val height: String,

    @Column(name = "depth")
    val depth: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "dimension_scale")
    val scale: DimensionScale
) {

    fun toDomain() = Dimension(width, height, depth, scale)
}

fun Dimension.toEntity() = MariaDBDimension(
    width = this.width,
    height = this.height,
    depth = this.depth,
    scale = this.scale
)
