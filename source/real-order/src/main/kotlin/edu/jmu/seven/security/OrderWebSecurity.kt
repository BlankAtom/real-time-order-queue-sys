package edu.jmu.seven.security

import edu.jmu.seven.exception.CustomizeAuthenticationEntryPoint
import edu.jmu.seven.handler.CustomizeAuthenticationFailureHandler
import edu.jmu.seven.handler.CustomizeAuthenticationSuccessHandler
import edu.jmu.seven.handler.CustomizeLogoutSuccessHandler
import edu.jmu.seven.service.impl.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/16:16
 */

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class OrderWebSecurity(
) : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var authenticationEntryPoint: CustomizeAuthenticationEntryPoint
    @Autowired
    lateinit var authenticationSuccessHandler: CustomizeAuthenticationSuccessHandler
    @Autowired
    lateinit var authenticationFailureHandler: CustomizeAuthenticationFailureHandler
    @Autowired
    lateinit var logoutSuccessHandler: CustomizeLogoutSuccessHandler

    override fun configure(http: HttpSecurity?) {
//        http?.authorizeRequests()?.antMatchers("/")?.permitAll();
        http!!
            .cors().and().csrf().disable()
        http
            .authorizeRequests()
            .antMatchers("/api/getUser").hasAuthority("USER")
            .and().exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .formLogin().permitAll()
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .and().logout().permitAll()
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID")

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService())
    }
    @Bean
    fun passwordEncoder() :BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
    @Bean
    fun userDetailService () : UserDetailsService {
        return UserService()
    }

}