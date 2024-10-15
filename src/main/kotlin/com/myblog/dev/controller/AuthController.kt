package com.myblog.dev.controller

import com.myblog.dev.auth.JwtUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "AUTH", description = "認証管理API")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil,
) {

    @Operation(summary = "ログイン", description = "ログインする")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping("/login")
    fun login(@RequestBody authRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        )

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authRequest.email)
        val jwt = jwtUtil.generateToken(userDetails)

        // ステートレスな設計にしてるのでここで設定するとステートフルになっちゃう。都度Frontから指定してきてもらう必要あり。
        // どっちがいいんやろな〜〜
        SecurityContextHolder.getContext().authentication = authentication

        return ResponseEntity.ok(LoginResponse(jwt))
    }
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val jwt: String
)

data class RegisterUserRequest(
    val username: String,
    val email: String,
    val password: String
)

data class RegisterUserResponse(
    val userId: String,
)

class User(
    val username: String,
    val password: String,
    val roles: List<String>,
)
