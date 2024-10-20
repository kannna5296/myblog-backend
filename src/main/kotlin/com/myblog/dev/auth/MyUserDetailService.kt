package com.myblog.dev.auth

import com.myblog.dev.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
        // TODO DB凸投げた実装
        // pass123想定のハッシュ化パスワード
        return User(user?.email, user?.password, emptyList())
    }
}
