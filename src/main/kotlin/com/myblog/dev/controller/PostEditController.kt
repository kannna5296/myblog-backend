package com.myblog.dev.controller

import com.myblog.dev.service.PostEditService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostEditController(
    private val service: PostEditService,
) {

    @PutMapping("/{id}")
    fun edit(@PathVariable id: String, @RequestBody form: PostEditForm): ResponseEntity<Void> {
        service.execute(id, form)
        return ResponseEntity.ok().build()
    }
}

class PostEditForm(
    val title: String,
    val content: String,
    val userId: String,
)
