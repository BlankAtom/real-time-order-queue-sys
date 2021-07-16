package edu.jmu.seven.service

import edu.jmu.seven.mapper.AccountMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/20:53
 */

@Service
class OrderUserDetailService : UserDetailsService {

    @Autowired
    private lateinit var userMapper: AccountMapper

    override fun loadUserByUsername(username: String?): UserDetails {
        val account = userMapper.selectByName(username?:"")
        val authes = ArrayList<SimpleGrantedAuthority>()
        authes.add( SimpleGrantedAuthority(account.auth_id.toString() ))
        return User(account.username, account.password, authes)
    }
}