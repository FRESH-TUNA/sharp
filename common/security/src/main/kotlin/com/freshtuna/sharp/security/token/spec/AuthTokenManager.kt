package com.freshtuna.sharp.security.token.spec

import com.freshtuna.sharp.id.SharpPublicID
import com.freshtuna.sharp.security.token.AuthToken

interface AuthTokenManager {

    /**
     * 토큰을 유저정보가 포함된 클래스로 변환한다.
     */
    fun validate(token: AuthToken)

    fun extractPublicId(token: AuthToken): SharpPublicID
}
