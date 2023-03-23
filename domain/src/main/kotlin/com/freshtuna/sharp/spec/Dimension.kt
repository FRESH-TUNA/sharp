package com.freshtuna.sharp.spec

import java.math.BigDecimal

data class Dimension(
    val width: BigDecimal,
    val height: BigDecimal,
    val depth: BigDecimal,
    val scale: DimensionScale
) {

    // helper methods for calculating volume and surface area
    fun volume(): BigDecimal {
        return width.multiply(height).multiply(depth)
    }

    fun surfaceArea(): BigDecimal {
        val wh = width.multiply(height)
        val wd = width.multiply(depth)
        val hd = height.multiply(depth)
        return wh.multiply(BigDecimal.valueOf(2))
            .add(wd.multiply(BigDecimal.valueOf(2)))
            .add(hd.multiply(BigDecimal.valueOf(2)))
    }

    // helper method for converting the dimensions to a different scale
    fun convertTo(newScale: DimensionScale): Dimension {
        val conversionFactor = when (scale) {
            DimensionScale.CM -> when (newScale) {
                DimensionScale.M -> BigDecimal.valueOf(0.01)
                DimensionScale.INCH -> BigDecimal.valueOf(0.3937)
                DimensionScale.FOOT -> BigDecimal.valueOf(0.03281)
                else -> BigDecimal.ONE
            }
            DimensionScale.M -> when (newScale) {
                DimensionScale.CM -> BigDecimal.valueOf(100)
                DimensionScale.INCH -> BigDecimal.valueOf(39.37)
                DimensionScale.FOOT -> BigDecimal.valueOf(3.281)
                else -> BigDecimal.ONE
            }
            DimensionScale.INCH -> when (newScale) {
                DimensionScale.CM -> BigDecimal.valueOf(2.54)
                DimensionScale.M -> BigDecimal.valueOf(0.0254)
                DimensionScale.FOOT -> BigDecimal.valueOf(0.08333)
                else -> BigDecimal.ONE
            }
            DimensionScale.FOOT -> when (newScale) {
                DimensionScale.CM -> BigDecimal.valueOf(30.48)
                DimensionScale.M -> BigDecimal.valueOf(0.3048)
                DimensionScale.INCH -> BigDecimal.valueOf(12)
                else -> BigDecimal.ONE
            }
        }

        val newWidth = width.multiply(conversionFactor)
        val newHeight = height.multiply(conversionFactor)
        val newDepth = depth.multiply(conversionFactor)

        return Dimension(newWidth, newHeight, newDepth, newScale)
    }
}
