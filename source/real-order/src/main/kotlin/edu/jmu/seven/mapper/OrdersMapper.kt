package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Orders
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface OrdersMapper : BaseMapper<Orders> {
    @Select("select * from orders")
    fun selectAllOrders() : List<Orders>
}