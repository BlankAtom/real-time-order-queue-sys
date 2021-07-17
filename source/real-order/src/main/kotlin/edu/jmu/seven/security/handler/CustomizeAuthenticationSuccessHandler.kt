package edu.jmu.seven.security.handler

import com.alibaba.fastjson.JSON
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.utils.ResultTool.success
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
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
    lateinit var sysUserService: AccountMapper

    override fun onAuthenticationSuccess(
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse,
        authentication: Authentication?
    ) {
        //更新用户表上次登录时间、更新人、更新时间等字段
        val userDetails: User = SecurityContextHolder.getContext().authentication.principal as User
        val sysUser = sysUserService.selectByName(userDetails.username)

        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        //返回json数据
        val result = success()
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.contentType = "text/json;charset=utf-8"
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.writer.write(JSON.toJSONString(result))
    }
}
