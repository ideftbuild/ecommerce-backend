package com.ideftbuild.ecommerce_backend.shared.infrastructure.filter

import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.JwtService
import com.ideftbuild.ecommerce_backend.user.application.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter (
    private val jwtService: JwtService,
    private val userDetailsService: CustomUserDetailsService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("control in filter")
        val authHeader = request.getHeader("Authorization")
        println("header gotten: $authHeader")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        println("header starts with Bearer. Proceeding")

        val token = authHeader.substring(7)
        val username = jwtService.extractUsername(token)

        println("token retrieved to be: $token")
        println("username to be: $username")
        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)

            println("successfully fetched users: ${userDetails.username}")

            val authToken  = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )

            println("returning authentication object")

            print("authorities are: ${userDetails.authorities}")

            SecurityContextHolder.getContext().authentication = authToken
        }

        filterChain.doFilter(request, response)
    }

}
