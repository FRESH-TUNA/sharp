package com.freshtuna.sharp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SharpMariadbApplication

fun main(args: Array<String>) {
    runApplication<SharpMariadbApplication>(*args)
}
