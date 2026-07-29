package com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence

import com.ideftbuild.ecommerce_backend.shared.application.port.input.TokenProvider
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService (

    @param:Value($$"${jwt.secret}")
    private val secret: String,

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())
): TokenProvider {
    override fun generateToken(username: String, roles: List<String>): String {
        println("JWT SECRET = '$secret'")
        println("SECRET LENGTH = ${secret.length}")
        return Jwts.builder()
            .subject(username)
            .claim("roles", roles)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    override fun extractUsername(token: String): String {
        println("JWT SECRET = '$secret'")
        println("SECRET LENGTH = ${secret.length}")
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    override fun isValid(token: String): Boolean {
        println("JWT SECRET = '$secret'")
        println("SECRET LENGTH = ${secret.length}")
        try {
            Jwts.parser()
                .decryptWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        } catch(e: Exception){
            return false
        }
    }
}
