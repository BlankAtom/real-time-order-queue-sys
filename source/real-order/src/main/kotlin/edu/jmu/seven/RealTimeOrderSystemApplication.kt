package edu.jmu.seven

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableOpenApi
class RealTimeOrderSystemApplication

fun main(args: Array<String>) {
    runApplication<RealTimeOrderSystemApplication>(*args)
}
