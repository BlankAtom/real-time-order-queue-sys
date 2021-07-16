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
@TableName("vocation.dish")
@ApiModel(value = "Dish对象", description = "")
public class Dish implements Serializable {


    private String d_id;

    private String d_name;

    private Integer m_id;

    private Double d_price;

    private String d_pic;

    private String d_note;

    private Integer d_sum;

    private String cuision_code;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    public String getd_id() {
        return d_id;
    }

    public void setd_id(String d_id) {
        this.d_id = d_id;
    }

    public String getd_name() {
        return d_name;
    }

    public void setd_name(String d_name) {
        this.d_name = d_name;
    }

    public Integer getm_id() {
        return m_id;
    }

    public void setm_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Double getd_price() {
        return d_price;
    }

    public void setd_price(Double d_price) {
        this.d_price = d_price;
    }

    public String getd_pic() {
        return d_pic;
    }

    public void setd_pic(String d_pic) {
        this.d_pic = d_pic;
    }

    public String getd_note() {
        return d_note;
    }

    public void setd_note(String d_note) {
        this.d_note = d_note;
    }

    public Integer getd_sum() {
        return d_sum;
    }

    public void setd_sum(Integer d_sum) {
        this.d_sum = d_sum;
    }

    public String getCuision_code() {
        return cuision_code;
    }

    public void setCuision_code(String cuision_code) {
        this.cuision_code = cuision_code;
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
        return "Dish{" +
        "d_id=" + d_id +
        ", d_name=" + d_name +
        ", m_id=" + m_id +
        ", d_price=" + d_price +
        ", d_pic=" + d_pic +
        ", d_note=" + d_note +
        ", d_sum=" + d_sum +
        ", cuision_code=" + cuision_code +
        ", created_at=" + created_at +
        ", updated_at=" + updated_at +
        "}";
    }
}
