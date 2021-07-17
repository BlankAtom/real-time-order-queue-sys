package edu.jmu.seven.service

import edu.jmu.seven.entity.Account
import edu.jmu.seven.exception.CustomException
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.RoleMapper
import edu.jmu.seven.utils.ResponseUserToken
import eu.bitwalker.useragentutils.DeviceType
import eu.bitwalker.useragentutils.UserAgent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.text.SimpleDateFormat

import java.time.LocalDateTime
import java.util.*

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/16:32
 */

@Service
class UserServiceImpl : UserService{

    @Autowired
    lateinit var accountMapper: AccountMapper

    @Autowired
    lateinit var roleMapper: RoleMapper

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    override fun register(user: Account) {
        val a = accountMapper.selectByName(user.username)


        // 加密
        val encoder = BCryptPasswordEncoder()
        user.password = encoder.encode(user.password)
        user.setAuth(1)
        accountMapper.insert(user)

    }

    override fun login(uname: String, pwd: String, userAgent: String): ResponseUserToken {
        // 用户验证
        val auth = authenticate(uname,pwd)

        // 存储认证信息
        SecurityContextHolder.getContext().authentication = auth

        // 生成token
        val account: Account = auth.principal as Account

        // 生成token
        val token = generateToken(userAgent, uname)

        // 存储token

        //
        return ResponseUserToken(token, account)
    }

    private fun generateToken(userAgent: String, uname: String): String {
        val token = StringBuilder()

        // 设备
        val ua: UserAgent = UserAgent.parseUserAgentString(userAgent)
        if( ua.operatingSystem.deviceType == DeviceType.MOBILE ) {
            token.append("MOBILE-")
        } else {
            token.append("PC-")
        }
        // 用户名
        token.append(DigestUtils.md5Digest(uname.toByteArray()))
        // 加时间
        token.append(SimpleDateFormat("yyyyMMddHHmmssSSSS").format(LocalDateTime.now()))

        // 三位随机数
        token.append(Random().nextInt(999 - 111 + 1) + 111)

        return token.toString()
    }

    private fun authenticate(un: String, pw: String) : Authentication{
        try {
            return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(un, pw))
        } catch (e: DisabledException) {
            throw CustomException(1, "认证失败: " + e.printStackTrace())
        } catch (e: BadCredentialsException) {
            throw CustomException(1, "认证失败: " + e.printStackTrace())
        }
    }
}