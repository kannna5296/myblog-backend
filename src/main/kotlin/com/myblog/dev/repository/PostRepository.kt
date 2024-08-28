package com.myblog.dev.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.Post.Companion.POST
import com.example.ktknowledgeTodo.infra.jooq.tables.User.Companion.USER
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
}
