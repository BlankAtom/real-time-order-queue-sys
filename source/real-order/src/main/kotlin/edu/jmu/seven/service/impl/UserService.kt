package edu.jmu.seven.service.impl

import edu.jmu.seven.entity.PermitUser
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.CustomerMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/14:53
 */
@Service
class UserService : UserDetailsService{
    @Autowired
    lateinit var accountMapper: AccountMapper

    @Autowired
    lateinit var customerMapper: CustomerMapper

    override fun loadUserByUsername(username: String?): UserDetails {
        val ua = accountMapper.selectByName(username?:"")
        val uc = customerMapper.selectByName(username?:"")
        val pu = PermitUser(username!!, ua.password, uc.c_phone)
        pu.setAuthorities(ua.auth)
        return User(ua.username, ua.password, pu.authorities)
    }
}