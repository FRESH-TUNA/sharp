package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.request.NewSkuRequest

import io.github.oshai.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    val log = KotlinLogging.logger {  }

    @GetMapping("/hello")
    fun new(): String {
        return "hello"
    }
}
