package edu.jmu.seven.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("vocation.customer")
@ApiModel(value = "Customer对象", description = "")
public class Customer implements Serializable {


    private String c_id;

    private String c_name;

    private String c_phone;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    public String getc_id() {
        return c_id;
    }

    public void setc_id(String c_id) {
        this.c_id = c_id;
    }

    public String getc_name() {
        return c_name;
    }

    public void setc_name(String c_name) {
        this.c_name = c_name;
    }

    public String getc_phone() {
        return c_phone;
    }

    public void setc_phone(String c_phone) {
        this.c_phone = c_phone;
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
        return "Customer{" +
                "c_id=" + c_id +
                ", c_name=" + c_name +
                ", c_phone=" + c_phone +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}";
    }
}
