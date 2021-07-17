package edu.jmu.seven.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/10:28
 */
@RestController
@RequestMapping("/api")
class UserController {

    /**
     * 校验token和用户名
     */
    @RequestMapping("/check")
    fun check() : String {
        return "123"
    }

    /**
     * 执行登录操作（调用顺序应该先调用check
     */
    @RequestMapping("/login")
    fun login() : String {
        return "login"
    }
}