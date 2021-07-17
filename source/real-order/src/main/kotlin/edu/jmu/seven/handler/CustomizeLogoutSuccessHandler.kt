package edu.jmu.seven.handler

import com.alibaba.fastjson.JSON
import edu.jmu.seven.utils.ResultTool.success
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @Author: Hutengfei
 * @Description: 登出成功处理逻辑
 * @Date Create in 2019/9/4 10:17
 */
@Component
class CustomizeLogoutSuccessHandler : LogoutSuccessHandler {
    @Throws(IOException::class, ServletException::class)
    override fun onLogoutSuccess(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        authentication: Authentication?
    ) {
        val result = success()
        httpServletResponse.contentType = "text/json;charset=utf-8"
        httpServletResponse.writer.write(JSON.toJSONString(result))
    }
}
