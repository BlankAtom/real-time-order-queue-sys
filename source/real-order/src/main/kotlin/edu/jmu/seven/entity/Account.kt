package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */

@TableName("vocation.account")
class Account(
    private var username: String ,
    private var password: String ,
    var auth_id: Int ,
    var created_at: LocalDateTime ,
    var updated_at: LocalDateTime ,
) : Serializable, UserDetails {

    override fun toString(): String {
        return "Account{" +
                "username=" + username +
                ", password=" + password +
                ", auth_id=" + auth_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}"
    }

    fun setUsername(s: String) { this.username = s }
    fun setPassword(s: String) { this.password = s }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if( auth_id!=0 ) {
            val gens = ArrayList<GrantedAuthority>()
            gens.add(SimpleGrantedAuthority(auth_id.toString()))
            return gens
        }
        return null!!
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isEnabled(): Boolean {
        return true
    }
}