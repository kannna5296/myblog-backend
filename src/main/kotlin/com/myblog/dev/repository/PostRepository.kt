package com.myblog.dev.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.Post.Companion.POST
import com.example.ktknowledgeTodo.infra.jooq.tables.User.Companion.USER
import com.example.ktknowledgeTodo.infra.jooq.tables.references.COMMENT
import com.myblog.dev.controller.CommentDetailDesponse
import com.myblog.dev.controller.PostDetailResponse
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

    @Transactional
    fun update(postId: Long, userId: Long, title: String, content: String) {
        dsl.update(POST)
            .set(POST.TITLE, title)
            .set(POST.CONTENT, content)
            .where(POST.ID.eq(postId))
            .execute()
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

    fun findById(postId: Long): PostDetailResponse {
        val postResult = dsl.select(
            POST.ID,
            POST.TITLE,
            POST.CONTENT,
        ).from(POST).where(POST.ID.eq(postId)).fetchOne() // TODO なかったら404

        val commentList = dsl.select(
            COMMENT.ID,
            COMMENT.USER_ID,
            COMMENT.CONTENT,
        ).from(COMMENT).where(COMMENT.POST_ID.eq(postId)).fetch()

        val commentResponse = commentList.map {
            CommentDetailDesponse(
                userId = it.getValue(COMMENT.USER_ID).toString(),
                content = it.getValue(COMMENT.CONTENT)!!,
            )
        }

        return PostDetailResponse(
            postId = postResult?.getValue(POST.ID).toString(),
            title = postResult?.getValue(POST.TITLE)!!,
            content = postResult.getValue(POST.CONTENT)!!,
            commentList = commentResponse,
        )
    }
}
