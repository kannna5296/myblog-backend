package com.myblog.dev.auth

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class MyExceptionHandler {

    @ExceptionHandler
    fun handleUnAuthorizedException(ex: UnAuthorizedException): ResponseEntity<String> {
        // カスタムメッセージを返す
        return ResponseEntity(ex.message, HttpStatus.FORBIDDEN)
    }
}