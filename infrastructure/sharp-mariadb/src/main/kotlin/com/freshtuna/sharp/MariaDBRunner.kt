package com.freshtuna.sharp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MariaDBRunner
fun main(args: Array<String>) {
    runApplication<MariaDBRunner>(*args)
}
