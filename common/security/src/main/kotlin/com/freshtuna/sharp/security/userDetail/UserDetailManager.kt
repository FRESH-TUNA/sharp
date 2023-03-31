package com.freshtuna.sharp.security.userDetail

import org.springframework.security.core.context.SecurityContextHolder

class UserDetailManager {

    companion object {
        fun get(): SharpUserDetail {
            return SecurityContextHolder.getContext().authentication.principal as SharpUserDetail
        }
    }
}
