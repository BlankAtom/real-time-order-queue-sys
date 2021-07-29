package edu.jmu.seven.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/28/16:38
 */

class SpringPasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence?): String {
        return BCryptPasswordEncoder().encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        println("Matched: $rawPassword, $encodedPassword")
        return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
    }


}