package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/13:20
 */


@TableName("vocation.oder_dish")
class OrderDish : Serializable {

    private var od_id: String? = null

    private var o_id: String? = null

    private var d_id: String? = null

    private var num: Int? = null


    fun getOd_id(): String? {
        return od_id
    }

    fun setOd_id(od_id: String?) {
        this.od_id = od_id
    }

    fun geto_id(): String? {
        return o_id
    }

    fun seto_id(o_id: String?) {
        this.o_id = o_id
    }

    fun getd_id(): String? {
        return d_id
    }

    fun setd_id(d_id: String?) {
        this.d_id = d_id
    }

    fun getNum(): Int? {
        return num
    }

    fun setNum(num: Int?) {
        this.num = num
    }

    override fun toString(): String {
        return "Oder_dish{" +
                "od_id=" + od_id +
                ", o_id=" + o_id +
                ", d_id=" + d_id +
                ", num=" + num +
                "}"
    }
}