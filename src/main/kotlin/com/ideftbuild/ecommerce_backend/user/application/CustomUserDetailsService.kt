package com.ideftbuild.ecommerce_backend.user.application

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.CustomUserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userOutputPort: UserOutputPort
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userOutputPort.findByUsernameOrEmail(username)
            ?: throw ResourceNotFoundException("user", message = username)


        return CustomUserDetails(
            id = user.id!!,
            username = user.username,
            password = user.password,
            roles = user.roles
        )
    }
}
