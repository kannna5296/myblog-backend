package com.myblog.dev

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class DevApplication

fun main(args: Array<String>) {
	runApplication<DevApplication>(*args)
}
