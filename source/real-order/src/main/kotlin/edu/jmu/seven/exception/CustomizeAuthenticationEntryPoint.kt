package edu.jmu.seven.exception

import com.alibaba.fastjson.JSON
import edu.jmu.seven.config.ResultCode
import edu.jmu.seven.utils.JsonResult
import edu.jmu.seven.utils.ResultTool
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @Description: 匿名用户访问无权限资源时的异常
 * @Date Create in 2019/9/3 21:35
 */
@Component
class CustomizeAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        e: AuthenticationException?
    ) {
        val result = ResultTool.fail(ResultCode.USER_NOT_LOGIN)
        httpServletResponse.contentType = "text/json;charset=utf-8"
        httpServletResponse.writer.write(JSON.toJSONString(result))
    }
}
