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
    lateinit var omapper: OrdersMapper

    @RequestMapping("/findOrdersBym_id")
    fun selectOrders(@RequestParam("m_id") m_id: String): List<Orders>? {
//        println(omapper.selectAllOrders())
        var orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
//        println("2222")
        return omapper.selectList(orderwrapper)
    }

    @RequestMapping("/findOrders")
    fun selectAll(): List<Orders>? {
        println("1111")
        return omapper.selectList(null)
    }

    @RequestMapping("/deleteOrderByo_id")
    fun deleteOrder(@RequestParam("o_id") o_id:String){
        var orderwrapper = QueryWrapper<Orders>().eq("o_id",o_id)
        omapper.delete(orderwrapper)
    }
}