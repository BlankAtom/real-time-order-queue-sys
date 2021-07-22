package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.OrdersMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/data")
class OrdersController {

    @Autowired
    lateinit var omapper: OrdersMapper

//    @PostMapping("/findOrdersBym_id")
    @RequestMapping("/findOrdersBym_id")
    fun selectOrders(@RequestParam("m_id") m_id: String): List<Orders>? {
//        println(omapper.selectAllOrders())
        var orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
//        println("2222")
        return omapper.selectList(orderwrapper)
    }
//    @GetMapping("/findOrdersBym_id/{m_id}")
//    fun selectOrders(@PathVariable("m_id") m_id: String,): List<Orders>? {
//    //        println(omapper.selectAllOrders())
//        var orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
//    //        println("2222")
//        return omapper.selectList(orderwrapper)
//    }
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

//    @RequestMapping("/findOrderByPage")
//    fun findPageOrders(@RequestParam("m_id") m_id:String, @RequestParam("curPage") curPage: Long,@RequestParam("size") size: Long):List<Orders>{
//        var orderPage = Page<Orders>(curPage,size);
//        val orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
//        orderPage = omapper.selectPage(orderPage,orderwrapper)
//        val orderList: List<Orders> = orderPage.records;
//        println(orderList)
//        return orderList
////        Page<Orders>() orderPage = new Page<>(curPage,size);
//    }
    @RequestMapping("/findOrderByPage")
    fun findPageOrders():List<Orders>{
        var orderPage = Page<Orders>(2,7);
        val orderwrapper = QueryWrapper<Orders>().eq("m_id", "merchant_8995566")
        var orderIPage:IPage<Orders> = omapper.selectPage(orderPage,orderwrapper)
        val orderList: List<Orders> = orderIPage.records;
        orderList.forEach { println(it.toString())}
        println(orderList.size)
        return orderList
//        Page<Orders>() orderPage = new Page<>(curPage,size);
    }
}