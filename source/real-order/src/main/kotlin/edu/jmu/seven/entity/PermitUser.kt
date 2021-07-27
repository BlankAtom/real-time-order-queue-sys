package edu.jmu.seven.entity

import net.sf.jsqlparser.statement.grant.Grant
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/14:20
 */
class PermitUser(
    private var username: String,
    private var password: String,
    var phone: String,

) : Serializable, UserDetails {

    private lateinit var authorities: ArrayList<GrantedAuthority>
    fun setAuthorities(i: Int) {
        this.authorities = ArrayList()
        when(i) {
            1 -> this.authorities.add(SimpleGrantedAuthority("ROLE_USER"))
            2 -> this.authorities.add(SimpleGrantedAuthority("ROLE_MERCHANT"))
            9 -> this.authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
    }

    override fun equals(other: Any?): Boolean {
        if( other is PermitUser ) {
            return other.getUsername() == this.username
        }
        return false
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}