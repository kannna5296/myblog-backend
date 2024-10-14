package com.myblog.dev.service

import com.myblog.dev.controller.PostDetailResponse
import com.myblog.dev.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostDetailService(
    private val postRepository: PostRepository,
) {

    fun execute(postId: Long): PostDetailResponse {
        return postRepository.findById(postId = postId)
    }
}
