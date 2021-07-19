package edu.jmu.seven.security.service

import edu.jmu.seven.entity.PermitUser
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.CustomerMapper
import edu.jmu.seven.mapper.MerchantMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
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

    @Autowired
    lateinit var merchantMapper: MerchantMapper

    override fun loadUserByUsername(username: String?): UserDetails {
        if( username == null ) {
            throw UsernameNotFoundException("传入了一个空的Username")
        }
        val ua = accountMapper.selectByName(username) ?: throw UsernameNotFoundException("用户名不存在")
        val uc = customerMapper.selectByName(username) ?: throw UsernameNotFoundException("不能在顾客中找到这个用户")

        val pu = PermitUser(username, ua.password, uc.c_phone)
        pu.setAuthorities(ua.auth)
        println(pu.toString())
        return User(username, ua.password, pu.authorities)
    }
}