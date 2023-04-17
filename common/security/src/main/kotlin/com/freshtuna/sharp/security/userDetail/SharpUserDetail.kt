package com.freshtuna.sharp.security.userDetail

import com.freshtuna.sharp.id.PublicId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SharpUserDetail(
    val id: PublicId,
    private val authorites: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return id.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
