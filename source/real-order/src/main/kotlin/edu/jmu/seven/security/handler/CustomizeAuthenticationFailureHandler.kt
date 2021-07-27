package edu.jmu.seven.security.handler

import com.alibaba.fastjson.JSON
import edu.jmu.seven.config.ResultCode
import edu.jmu.seven.utils.JsonResult
import edu.jmu.seven.utils.ResultTool.fail
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @Author: Hutengfei
 * @Description: 登录失败处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
class CustomizeAuthenticationFailureHandler : AuthenticationFailureHandler {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        e: AuthenticationException?
    ) {
        //返回json数据
        val result: JsonResult<*> = when (e) {
            is AccountExpiredException -> {
                //账号过期
                fail(ResultCode.USER_ACCOUNT_EXPIRED)
            }
            is BadCredentialsException -> {
                //密码错误
                fail(ResultCode.USER_CREDENTIALS_ERROR)
            }
            is CredentialsExpiredException -> {
                //密码过期
                fail(ResultCode.USER_CREDENTIALS_EXPIRED)
            }
            is DisabledException -> {
                //账号不可用
                fail(ResultCode.USER_ACCOUNT_DISABLE)
            }
            is LockedException -> {
                //账号锁定
                fail(ResultCode.USER_ACCOUNT_LOCKED)
            }
            is InternalAuthenticationServiceException -> {
                //用户不存在
                fail(ResultCode.USER_ACCOUNT_NOT_EXIST)
            }
            else -> {
                //其他错误
                fail(ResultCode.COMMON_FAIL)
            }
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.contentType = "text/json;charset=utf-8"
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.writer.write(JSON.toJSONString(result))
    }
}
