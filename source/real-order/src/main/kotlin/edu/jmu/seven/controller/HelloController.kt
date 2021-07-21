package edu.jmu.seven.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>这里是用来做简单Controller测试的类</p>
 *
 * @author github/blackswords
 * @date 2021/07/16/20:08
 */

@RestController
class HelloController {
    @PreAuthorize("hasRole('user')")
    @RequestMapping("/hello")
    fun hello() : String {
        return "ehllo"
    }

    @RequestMapping("/api/getUser")
    fun gu( ) : String {

        return "{\"getUser\": \"user\"}"
    }
}