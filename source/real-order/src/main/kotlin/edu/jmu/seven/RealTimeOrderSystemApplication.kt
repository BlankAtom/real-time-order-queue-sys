package edu.jmu.seven

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@MapperScan("edu.jmu.seven.mapper")
class RealTimeOrderSystemApplication

fun main(args: Array<String>) {
    runApplication<RealTimeOrderSystemApplication>(*args)
}
