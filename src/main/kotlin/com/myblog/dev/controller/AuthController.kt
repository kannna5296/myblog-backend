package com.myblog.dev.controller

import com.myblog.dev.auth.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        )

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authRequest.email)
        val jwt = jwtUtil.generateToken(userDetails)

        // ステートレスな設計にしてるのでここで設定するとステートフルになっちゃう。都度Frontから指定してきてもらう必要あり。
        // どっちがいいんやろな〜〜
        SecurityContextHolder.getContext().authentication = authentication

        return ResponseEntity.ok(AuthResponse(jwt))
    }

    @PostMapping("/register")
    fun register(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        // ユーザー登録処理を実装。ユーザー情報を保存した後、JWTを返す。

        val user = User(
            username = authRequest.email,
            password = passwordEncoder.encode(authRequest.password),
            roles = listOf()
        )

        // ユーザー保存処理（リポジトリなどを利用）

        val jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.username))
        return ResponseEntity.ok(AuthResponse(jwt))
    }
}

data class AuthRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val jwt: String
)

class User(
    val username: String,
    val password: String,
    val roles: List<String>,
)
