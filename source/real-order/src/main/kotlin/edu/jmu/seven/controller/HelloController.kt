package edu.jmu.seven.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/20:08
 */

@RestController
class HelloController {
    @RequestMapping("/hello")
    fun hello() : String {
        return "ehllo"
    }
}