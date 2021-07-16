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
@TableName("vocation.orders")
@ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {


    private String o_id;

    private String m_id;

    private String c_id;

    private Double o_cost;

    private String o_content;

    private LocalDateTime o_start_time;

    private LocalDateTime o_pay_time;

    private Integer o_pay_type;

    private String o_estimate;

    private String status;

    private Integer o_desk_num;

    private LocalDateTime created_at;

    private LocalDateTime update_at;

    private Integer deleted;

    private Integer version;


    public String geto_id() {
        return o_id;
    }

    public void seto_id(String o_id) {
        this.o_id = o_id;
    }

    public String getm_id() {
        return m_id;
    }

    public void setm_id(String m_id) {
        this.m_id = m_id;
    }

    public String getc_id() {
        return c_id;
    }

    public void setc_id(String c_id) {
        this.c_id = c_id;
    }

    public Double geto_cost() {
        return o_cost;
    }

    public void seto_cost(Double o_cost) {
        this.o_cost = o_cost;
    }

    public String geto_content() {
        return o_content;
    }

    public void seto_content(String o_content) {
        this.o_content = o_content;
    }

    public LocalDateTime geto_start_time() {
        return o_start_time;
    }

    public void seto_start_time(LocalDateTime o_start_time) {
        this.o_start_time = o_start_time;
    }

    public LocalDateTime geto_pay_time() {
        return o_pay_time;
    }

    public void seto_pay_time(LocalDateTime o_pay_time) {
        this.o_pay_time = o_pay_time;
    }

    public Integer geto_pay_type() {
        return o_pay_type;
    }

    public void seto_pay_type(Integer o_pay_type) {
        this.o_pay_type = o_pay_type;
    }

    public String geto_estimate() {
        return o_estimate;
    }

    public void seto_estimate(String o_estimate) {
        this.o_estimate = o_estimate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer geto_desk_num() {
        return o_desk_num;
    }

    public void seto_desk_num(Integer o_desk_num) {
        this.o_desk_num = o_desk_num;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Orders{" +
        "o_id=" + o_id +
        ", m_id=" + m_id +
        ", c_id=" + c_id +
        ", o_cost=" + o_cost +
        ", o_content=" + o_content +
        ", o_start_time=" + o_start_time +
        ", o_pay_time=" + o_pay_time +
        ", o_pay_type=" + o_pay_type +
        ", o_estimate=" + o_estimate +
        ", status=" + status +
        ", o_desk_num=" + o_desk_num +
        ", created_at=" + created_at +
        ", update_at=" + update_at +
        ", deleted=" + deleted +
        ", version=" + version +
        "}";
    }
}
