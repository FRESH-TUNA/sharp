package com.freshtuna.sharp.oh

import com.freshtuna.sharp.oh.exception.SharpException
import com.freshtuna.sharp.oh.exception.SharpMsgMapException

enum class Oh(
    val code: String,
    val explanation: String
) {

    /**
     * client side error
     */
    FORM_VALIDATION_ERROR("SHARP-C1", "form 검증에 실패했습니다."),
    AUTHENTICATION_ERROR("SHARP-C2", "인증에 실패앴습니다."),
    /**
     * internal server error
     */
    INTERNAL_SERVER_ERROR("ERROR", "알수 없는 오류 입니다.");

    companion object {
        fun formValidationError(errorMap: Map<String, String>): Nothing {
            throw SharpMsgMapException(FORM_VALIDATION_ERROR, errorMap)
        }

        fun authenticationError(msg: String): Nothing {

            throw SharpException(AUTHENTICATION_ERROR)
        }
    }
}
