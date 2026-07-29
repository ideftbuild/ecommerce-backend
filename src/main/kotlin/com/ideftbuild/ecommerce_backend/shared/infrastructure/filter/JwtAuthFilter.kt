package com.ideftbuild.ecommerce_backend.shared.infrastructure.filter

import com.ideftbuild.ecommerce_backend.shared.api.AuthenticationEntryPoint
import com.ideftbuild.ecommerce_backend.shared.exception.JwtExpiredAuthenticationException
import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.JwtService
import com.ideftbuild.ecommerce_backend.user.application.CustomUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
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
    private val userDetailsService: CustomUserDetailsService,
    private val authenticationEntryPoint: AuthenticationEntryPoint
): OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath in listOf(
            "/api/v1/auth/login",
            "/api/v1/auth/signup"
        )
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("Ignoring do filter internal")
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)

        try {
            val username = jwtService.extractUsername(token)

            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(username)

                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                SecurityContextHolder.getContext().authentication = authToken
                println("Authentication name is : ${authToken.name}")
            }
        } catch (ex: ExpiredJwtException) {
            authenticationEntryPoint.commence(
                request,
                response,
                JwtExpiredAuthenticationException(
                    "Your JWT token has expired",
                    ex
                )
            )
            return
        }

        filterChain.doFilter(request, response)
    }
}
