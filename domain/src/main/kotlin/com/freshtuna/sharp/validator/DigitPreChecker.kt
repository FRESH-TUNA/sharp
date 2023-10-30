package com.freshtuna.sharp.validator

import com.freshtuna.sharp.oh.Oh
import java.math.BigDecimal

class DigitPreChecker {

    companion object {

        fun check(value: String?): String {
            if (value.isNullOrBlank())
                return ""

            val target = value!!.trim()

            try {
                BigDecimal(target)
            } catch (e: NumberFormatException) {
                Oh.badRequest()
            }

            return target
        }
    }
}
