package com.myblog.dev.service

import com.myblog.dev.controller.PostIndexResponse
import com.myblog.dev.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostIndexService(
    private val postRepository: PostRepository,
) {

    fun execute(userId: Long): List<PostIndexResponse> {
        return postRepository.search(userId = userId)
    }
}
