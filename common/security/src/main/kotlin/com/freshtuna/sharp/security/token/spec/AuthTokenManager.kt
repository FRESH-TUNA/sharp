package com.freshtuna.sharp.security.token.spec

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.member.constant.Role
import com.freshtuna.sharp.security.token.AuthToken

interface AuthTokenManager {

    /**
     * 토큰을 유저정보가 포함된 클래스로 변환한다.
     */
    fun validate(token: AuthToken)

    fun extractPublicId(token: AuthToken): SharpID

    fun extractRoles(token: AuthToken): List<Role>
}
