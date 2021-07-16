package edu.jmu.seven.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.account")
@ApiModel(value = "Account对象", description = "")
public class Account implements Serializable {


    private String username;

    @ApiModelProperty(value = "密码的MD5码")
    private String password;

    private Integer auth_id;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Integer auth_id) {
        this.auth_id = auth_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Account{" +
        "username=" + username +
        ", password=" + password +
        ", auth_id=" + auth_id +
        ", created_at=" + created_at +
        ", updated_at=" + updated_at +
        "}";
    }
}
