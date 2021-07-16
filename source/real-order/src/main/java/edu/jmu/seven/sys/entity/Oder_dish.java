package edu.jmu.seven.sys.entity;

import java.io.Serializable;

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
@TableName("vocation.oder_dish")
@ApiModel(value = "Oder_dish对象", description = "")
public class Oder_dish implements Serializable {


    private String od_id;

    private String o_id;

    private String d_id;

    private Integer num;


    public String getOd_id() {
        return od_id;
    }

    public void setOd_id(String od_id) {
        this.od_id = od_id;
    }

    public String geto_id() {
        return o_id;
    }

    public void seto_id(String o_id) {
        this.o_id = o_id;
    }

    public String getd_id() {
        return d_id;
    }

    public void setd_id(String d_id) {
        this.d_id = d_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Oder_dish{" +
        "od_id=" + od_id +
        ", o_id=" + o_id +
        ", d_id=" + d_id +
        ", num=" + num +
        "}";
    }
}
