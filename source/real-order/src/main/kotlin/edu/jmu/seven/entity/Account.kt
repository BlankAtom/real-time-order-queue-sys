package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
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
    /**
     * username: 字段，用户名，自动生成策略：user+电话号码
     */
    @TableId("username")
    private var username: String,

    /**
     * password: 字段，密码（已加密），可以使用SHA512加密算法（128长度）
     * 加密算法可以调用 class： SHAUtil
     */
    private var password: String,

    /**
     * auth_id: 字段，权限码，1、2、9，用户、商家、管理员
     */
    @TableField(value = "auth_id")
    private var auth: Int,
) : Serializable, UserDetails {


    /**
     * createTime: 字段，创建时间，记录创建记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "created_at" ,fill = FieldFill.INSERT)
    var createTime: LocalDateTime = LocalDateTime.now()

    /**
     * updateTime: 字段，更新时间，记录更新记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime = LocalDateTime.now()


    /**
     * deleted: 字段，逻辑删除，表示是否被删除，当为1（删除）的时候不会被搜索到
     * 不需要开发者进行设定，@TableLogic 会自己完成
     */
    @TableLogic
    var deleted: Int = 0

    /**
     * version: 字段，乐观锁，用于插入、更新时进行版本比较
     * 不需要开发者进行设定，@Version 会自己完成
     */
    @Version
    var version: Int = 1

    /**
     * 转为类名+json格式的字符串
     * @return 全部可现实内容的组合字符串
     */
    override fun toString(): String {
        return "Account{" +
                "username=" + username +
                ", password=" + password +
                ", auth_id=" + auth +
                ", created_at=" + createTime +
                ", updated_at=" + updateTime +
                "}"
    }

    fun setUsername(s: String) { this.username = s }
    fun setPassword(s: String) { this.password = s }

    fun setAuth(i: Int) { this.auth = i }
    fun getAuth() : Int { return this.auth }

    /**
     * 获取权限表，因为本项目的设定是通过数字确定权限，故，返回只含有一个数字的权限集合即可
     * @return MutableCollection 权限表，可以直接当作List使用
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if( auth!=0 ) {
            val gens = ArrayList<GrantedAuthority>()
            gens.add(SimpleGrantedAuthority(auth.toString()))
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