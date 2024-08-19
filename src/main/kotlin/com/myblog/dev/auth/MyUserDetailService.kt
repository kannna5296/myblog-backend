package com.myblog.dev.auth

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class MyUserDetailService(
    //private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        //val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
        // TODO DB凸投げた実装
        //pass123想定のハッシュ化パスワード
        return User("admin@example.com", "\$2a\$10\$L9A5chddB8TH4mhneAUfEe5kuLGc2wGLKVIqfJew.KpU9F5MZ/oCW", emptyList())
    }
}