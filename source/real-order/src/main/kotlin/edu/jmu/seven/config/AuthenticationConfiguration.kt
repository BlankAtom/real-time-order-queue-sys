package edu.jmu.seven.config

import org.springframework.context.annotation.Configuration

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/19/10:59
 */

@Configuration
class AuthenticationConfiguration {
    companion object UserCode{
        val COSTOMER_CODE = 1
        val MERCHANT_CODE = 2
        val ADMIN_CODE = 9
    }
}