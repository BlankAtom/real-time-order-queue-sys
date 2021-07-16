package edu.jmu.seven.sys.entity;

import java.io.Serializable;
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
@TableName("vocation.cuisine")
@ApiModel(value = "Cuisine对象", description = "")
public class Cuisine implements Serializable {


    private Integer code;

    private String name;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
        "code=" + code +
        ", name=" + name +
        "}";
    }
}
