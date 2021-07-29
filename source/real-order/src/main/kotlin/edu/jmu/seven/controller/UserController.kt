package edu.jmu.seven.controller

import com.alibaba.fastjson.JSON
import edu.jmu.seven.config.ResultCode
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.account.ResponseAccount
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.utils.LoginUtil
import edu.jmu.seven.utils.ResultTool.fail
import edu.jmu.seven.utils.ResultTool.success
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
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
    private lateinit var accountMapper: AccountMapper
    /**
     * 校验token和用户名
     * @return code: 结果码, success: true/false
     */
    @RequestMapping("/check")
    fun check(@RequestParam("token") token: String) : String {
        // 查询token的时效，根据结果返回 token-uname-auth

        return "123"
    }

    /**
     * 执行登录操作（调用顺序应该先调用check）
     * 插入token数据并返回token-username-auth三个数据（包装为实体类）
     * @param act Account 可以只带有username和password
     */
    @RequestMapping("/login")
    fun login(@RequestBody act: Account) : String {
        // 查询数据库
        val account = accountMapper.selectByName(act.username)

        // 验证密码
        if(account != null && BCryptPasswordEncoder().matches(act.password, account.password)) {
            // 验证成功
            // 生成token
            val token = LoginUtil.generateToken(account.username)
            // 生成权限字符串
            val auth: String = LoginUtil.generateAuth(account.auth)
            // 此处应该插入数据库，保存token时间

            // 生成成功信息
            val res = success(ResponseAccount(token, act.username, auth))
            return JSON.toJSONString(res)
        }

        // 密码错误，返回错误信息
        return JSON.toJSONString(fail(ResultCode.USER_CREDENTIALS_ERROR))
    }
}