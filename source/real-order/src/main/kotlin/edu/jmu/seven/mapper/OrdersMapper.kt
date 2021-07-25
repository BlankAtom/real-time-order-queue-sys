package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Orders
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Update


@Mapper
interface OrdersMapper : BaseMapper<Orders> {

    @Update("update orders set status='closed' where o_id = #{id} ")
    fun updateStatusById(@Param(value = "id") id: String) : Int?

    @Update("update orders set o_cost=#{cost},o_pay_type=#{type} where o_id = #{id}")
    fun updateOrderById(@Param(value = "cost") cost: Double,@Param(value = "type") type: Int, @Param(value = "id") id: String) : Int?
}