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
@TableName("vocation.merchant")
@ApiModel(value = "Merchant对象", description = "")
public class Merchant implements Serializable {


    private String m_id;

    private String m_name;

    private String m_license;

    private String m_phone;

    private String m_address;

    private String m_open_time;

    private String m_close_time;

    private String m_tag;

    private String m_notice;

    private String m_icon;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    public String getm_id() {
        return m_id;
    }

    public void setm_id(String m_id) {
        this.m_id = m_id;
    }

    public String getm_name() {
        return m_name;
    }

    public void setm_name(String m_name) {
        this.m_name = m_name;
    }

    public String getm_license() {
        return m_license;
    }

    public void setm_license(String m_license) {
        this.m_license = m_license;
    }

    public String getm_phone() {
        return m_phone;
    }

    public void setm_phone(String m_phone) {
        this.m_phone = m_phone;
    }

    public String getm_address() {
        return m_address;
    }

    public void setm_address(String m_address) {
        this.m_address = m_address;
    }

    public String getm_open_time() {
        return m_open_time;
    }

    public void setm_open_time(String m_open_time) {
        this.m_open_time = m_open_time;
    }

    public String getm_close_time() {
        return m_close_time;
    }

    public void setm_close_time(String m_close_time) {
        this.m_close_time = m_close_time;
    }

    public String getm_tag() {
        return m_tag;
    }

    public void setm_tag(String m_tag) {
        this.m_tag = m_tag;
    }

    public String getm_notice() {
        return m_notice;
    }

    public void setm_notice(String m_notice) {
        this.m_notice = m_notice;
    }

    public String getm_icon() {
        return m_icon;
    }

    public void setm_icon(String m_icon) {
        this.m_icon = m_icon;
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
        return "Merchant{" +
        "m_id=" + m_id +
        ", m_name=" + m_name +
        ", m_license=" + m_license +
        ", m_phone=" + m_phone +
        ", m_address=" + m_address +
        ", m_open_time=" + m_open_time +
        ", m_close_time=" + m_close_time +
        ", m_tag=" + m_tag +
        ", m_notice=" + m_notice +
        ", m_icon=" + m_icon +
        ", created_at=" + created_at +
        ", updated_at=" + updated_at +
        "}";
    }
}
