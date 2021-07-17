package edu.jmu.seven.security

import edu.jmu.seven.service.OrderUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/16:16
 */

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class OrderWebSecurity(
    @Autowired var  userDetailsService: OrderUserDetailService
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
//        http?.authorizeRequests()?.antMatchers("/")?.permitAll();
        http!!//.csrf().disable()
            //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and()
            .authorizeRequests()
        // register 和 login 不需要验证就可以访问，其他需要token
            .antMatchers("/hello", "/api/login", "/api/register", "/error/*")
            .permitAll()
            .anyRequest().authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(BCryptPasswordEncoder())
    }


    /**
     * 当AuthenticationManager无法注入时，重写这个方法，加 Bean 注解解决
     */
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}