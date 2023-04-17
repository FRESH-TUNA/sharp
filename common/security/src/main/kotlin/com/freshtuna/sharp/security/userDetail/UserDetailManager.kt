package com.freshtuna.sharp.security.userDetail

import com.freshtuna.sharp.id.PublicId
import org.springframework.security.core.context.SecurityContextHolder

class UserDetailManager {

    companion object {
        fun getUserDetail()
            = SecurityContextHolder.getContext().authentication.principal as SharpUserDetail

        fun getPublicId() = getUserDetail().id
    }
}
