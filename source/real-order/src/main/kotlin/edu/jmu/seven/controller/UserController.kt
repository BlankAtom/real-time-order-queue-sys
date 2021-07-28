package edu.jmu.seven.controller

import edu.jmu.seven.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/10:28
 */
@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    private lateinit var loginService: LoginService
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
    fun login(@RequestParam("username") username: String,
              @RequestParam("password") password: String
    ) : String {
        val token = loginService.login(username, password)

        return token
    }
}