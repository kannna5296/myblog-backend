package com.myblog.dev.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.Post.Companion.POST
import com.example.ktknowledgeTodo.infra.jooq.tables.User.Companion.USER
import com.myblog.dev.controller.PostIndexResponse
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PostRepository(private val dsl: DSLContext) {

    @Transactional
    fun insert(userId: Long, title: String, content: String): Long {
        val result = dsl.insertInto(POST)
            .set(POST.USER_ID, userId)
            .set(POST.TITLE, title)
            .set(POST.CONTENT, content)
            .returning(POST.ID).fetchOne()
        return result?.getValue(USER.ID)!!
    }

    fun search(userId: Long): List<PostIndexResponse> {
        val result = dsl.select(
            POST.ID,
            POST.TITLE,
        ).from(POST).where(POST.USER_ID.eq(userId)).fetch()
        return result.map {
            PostIndexResponse(
                postId = it.getValue(POST.ID).toString(),
                title = it.getValue(POST.TITLE)!!,
            )
        }
    }
}
