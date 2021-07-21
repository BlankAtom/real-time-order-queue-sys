package edu.jmu.seven.security

import edu.jmu.seven.exception.CustomizeAuthenticationEntryPoint
import edu.jmu.seven.security.handler.CustomizeAuthenticationFailureHandler
import edu.jmu.seven.security.handler.CustomizeAuthenticationSuccessHandler
import edu.jmu.seven.security.handler.CustomizeLogoutSuccessHandler
import edu.jmu.seven.security.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import kotlin.coroutines.coroutineContext

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
    lateinit var userService: UserService
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


    @Bean
    fun corsFilter() : CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val cors = CorsConfiguration()
        cors.addAllowedOrigin("http://localhost:3000")
        cors.addAllowedHeader("*")
        cors.addAllowedMethod("*")
        cors.allowCredentials = true

        source.registerCorsConfiguration("/**", cors)
        return CorsFilter(source)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userService)
    }
    @Bean
    fun passwordEncoder() :BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

}