package com.myblog.dev.controller

import com.myblog.dev.auth.JwtUtil
import com.myblog.dev.service.UserRegisterService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "USER", description = "ユーザ管理API")
class UserRegisterController(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder,
    private val userRegisterService: UserRegisterService,
) {

    @Operation(summary = "ユーザ登録", description = "ユーザを登録する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> {
        // TODO ユーザー登録処理を実装。ユーザー情報を保存した後、JWTを返す。

        val user = User(
            username = request.email,
            password = passwordEncoder.encode(request.password),
            roles = listOf()
        )
        val jwt = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.username))

        userRegisterService.register(form = request)
        return ResponseEntity.ok(RegisterUserResponse(jwt))
    }
}
