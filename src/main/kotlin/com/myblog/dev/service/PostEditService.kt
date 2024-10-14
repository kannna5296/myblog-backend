package com.myblog.dev.service

import com.myblog.dev.controller.PostEditForm
import com.myblog.dev.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostEditService(
    private val postRepository: PostRepository,
) {

    @Transactional
    fun execute(postId: String, form: PostEditForm) {
        postRepository.update(
            postId = postId.toLong(),
            userId = form.userId.toLong(),
            title = form.title,
            content = form.content
        )
    }
}
