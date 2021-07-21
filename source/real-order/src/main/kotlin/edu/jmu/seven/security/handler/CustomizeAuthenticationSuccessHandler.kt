package edu.jmu.seven.security.handler

import com.alibaba.fastjson.JSON
import edu.jmu.seven.config.AuthenticationConfiguration
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.AccountTokenEntity
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.utils.JsonResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @Author: Hutengfei
 * @Description: 登录成功处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
class CustomizeAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    @Autowired
    lateinit var accountMapper: AccountMapper

    override fun onAuthenticationSuccess(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        authentication: Authentication?
    ) {
        //更新用户表上次登录时间、更新人、更新时间等字段
        val userDetails: User = SecurityContextHolder.getContext().authentication.principal as User
        val account = accountMapper.selectByName(userDetails.username)
            ?: throw UsernameNotFoundException("通过username：${userDetails.username}找不到用户")

        //此处返回几个数据：
        // 1、token，2、权限（user, merchant, admin)

        val tokenResponse = AccountTokenEntity(generateToken(account), userDetails.username, getRoleString(account.auth))
        val result = JsonResult(true, tokenResponse)

        //处理编码方式，防止中文乱码的情况
        httpServletResponse.contentType = "text/json;charset=utf-8"

        //塞到HttpServletResponse中返回给前台
        //返回json数据
        httpServletResponse.writer.write(JSON.toJSONString(result))
    }

    /**
     * 根据一个User获得一个token
     * @param user Account 类型的实例，主要是为了传入用户名和auth
     * @return token
     */
    private fun generateToken( user: Account): String {
        val token = StringBuilder()

        // 用户名
        token.append(DigestUtils.md5Digest(user.username.toByteArray()))
        // 加时间,获得时间格式为：yyyyMMddHHmmssSSS 的码
        token.append(SimpleDateFormat("yyyyMMddHHmmssSSS").format(Date()))

        // 加角色，将角色字符串加入token
        token.append(getRoleString(user.auth))
        // 三位随机数
        token.append(Random().nextInt(999 - 111 + 1) + 111)

        return token.toString()
    }

    fun getRoleString(i: Int) : String  {
        return when (i){
            AuthenticationConfiguration.COSTOMER_CODE   -> "customer"
            AuthenticationConfiguration.MERCHANT_CODE   -> "merchant"
            AuthenticationConfiguration.ADMIN_CODE      -> "admin"
            else -> "user"
        }
    }
}
