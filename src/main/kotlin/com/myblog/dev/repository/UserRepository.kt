package com.myblog.dev.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.User.Companion.USER
import com.example.ktknowledgeTodo.infra.jooq.tables.records.UserRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserRepository(private val dsl: DSLContext) {

    @Transactional
    fun insertUser(username: String, email: String, password: String): Long {
        val result = dsl.insertInto(USER)
            .set(USER.USERNAME, username)
            .set(USER.EMAIL, email)
            .set(USER.PASSWORD, password)
            .returning(USER.ID).fetchOne()

        return result?.getValue(USER.ID)!!
    }

    fun findByEmail(email: String): UserRecord? {
        val result = dsl.selectFrom(USER)
            .where(USER.EMAIL.eq(email))
            .fetchOne()
        return result
    }
}
