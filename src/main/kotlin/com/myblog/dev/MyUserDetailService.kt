package com.myblog.dev

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
        return User("admin", "pass123", emptyList())
    }
}