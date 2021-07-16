package edu.jmu.seven

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("edu.jmu.seven.mapper")
class RealTimeOrderSystemApplication

fun main(args: Array<String>) {
    runApplication<RealTimeOrderSystemApplication>(*args)
}
