package com.myblog.dev.service

import com.myblog.dev.controller.RegisterUserRequest
import com.myblog.dev.controller.RegisterUserResponse
import com.myblog.dev.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRegisterService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun register(form: RegisterUserRequest): RegisterUserResponse {
        val insertedId = userRepository.insertUser(
            username = form.username,
            email = form.email,
            password = form.password,
        )
        return RegisterUserResponse(userId = insertedId.toString())
    }
}
