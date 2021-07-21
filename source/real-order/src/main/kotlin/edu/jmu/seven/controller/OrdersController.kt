package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.OrdersMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/data")
class OrdersController {

    @Autowired
    lateinit var mapper: OrdersMapper

    @RequestMapping("/findOneOrders")
    fun selectOrders(@RequestParam("m_id") m_id: String): List<Orders>? {
        println(mapper.selectAllOrders())
        var warrper = QueryWrapper<Orders>().eq("m_id",m_id);

        return mapper.selectList(warrper)
    }

    @RequestMapping("/findOrders")
    fun selectAll(): List<Orders>? {


        return mapper.selectList(null)
    }
}