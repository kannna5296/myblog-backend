package com.myblog.dev.service

import com.myblog.dev.controller.PostRegisterForm
import com.myblog.dev.controller.PostRegisterResponse
import com.myblog.dev.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostRegisterService(
    private val postRepository: PostRepository,
) {

    @Transactional
    fun execute(form: PostRegisterForm): PostRegisterResponse {
        val insertedId = postRepository.insert(
            userId = form.userId.toLong(),
            title = form.title,
            content = form.content
        )
        return PostRegisterResponse(postId = insertedId.toString())
    }
}
