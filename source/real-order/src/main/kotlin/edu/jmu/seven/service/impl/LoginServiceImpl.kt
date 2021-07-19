package edu.jmu.seven.service.impl

import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.service.LoginService
import edu.jmu.seven.service.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/13:30
 */

@Service
class LoginServiceImpl : LoginService {

    companion object {
        /**
         * 登陆成功
         */
        val SUCCESS_CODE = 10000

        /**
         * 登陆失败
         */
        val DEFAILD_CODE = 10001

        /**
         * 密码错误
         */
        val ERROR_PWD = 10002

        /**
         * 注销成功
         */
        val LOGOUT_SUCCESS_CODE = 20000

        /**
         * 注销失败（失败的可能性一般为没有登录，外层接收到失败后可以跳转到登录
         */
        val LOGOUT_FAILURE_CODE = 20001
//        val ERROR_
        val SEVEN_DAY_SEC: Long = 7 * 24 * 60 * 60
    }

    @Autowired
    lateinit var redisService: RedisService

    @Autowired
    lateinit var accountMapper: AccountMapper


    override fun check(user: String) : Boolean {
        redisService.get(user) ?: return false
        return true
    }

    override fun login(phone: String, pwd: String) : String {
        val a = accountMapper.selectByName("user_$phone")
        if ( a.password == pwd ) {
            val token = generateToken("user_$phone")
            redisService.set(token, a.username)
            return token
        }
        return ""
    }

    override fun logout(token: String) : Boolean {
        return redisService.delete(token)
    }


    private fun generateToken( uname: String): String {
        val token = StringBuilder()

        // 用户名
        token.append(DigestUtils.md5Digest(uname.toByteArray()))
        // 加时间
        token.append(SimpleDateFormat("yyyyMMddHHmmssSSSS").format(LocalDateTime.now()))

        // 三位随机数
        token.append(Random().nextInt(999 - 111 + 1) + 111)

        return token.toString()
    }
}