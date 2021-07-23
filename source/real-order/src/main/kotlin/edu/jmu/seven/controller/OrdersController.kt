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


    @RequestMapping("/findOrdersBym_id")
    fun selectOrders(
        @RequestParam("m_id") m_id: String
    ): List<Orders>? {
        println(m_id)
        var orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)

        return omapper.selectList(orderwrapper)
    }

    @RequestMapping("/deleteOrderByo_id")
    fun deleteOrder(
        @RequestParam("o_id") o_id:String
    ){
        var orderwrapper = QueryWrapper<Orders>().eq("o_id",o_id)
//        println(omapper.selectList(orderwrapper))
        omapper.delete(orderwrapper)
    }

    @RequestMapping("/findOrderByPage")
    fun findPageOrders(
        @RequestParam("m_id") m_id:String,
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ):List<Orders>{
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        var orderPage = Page<Orders>(curPageLong,pageCountLong);
        val orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
        orderPage = omapper.selectPage(orderPage,orderwrapper)
        val orderList: List<Orders> = orderPage.records;
        return orderList
    }
//    @RequestMapping("/findOrderByPage1")
//    fun findPageOrders():List<Orders>{
//        var orderPage: Page<Orders> = Page<Orders>(1,7);
//        val orderwrapper = QueryWrapper<Orders>().eq("m_id", "merchant_8995566")
//        var orderIPage:IPage<Orders> = omapper.selectPage(orderPage,null)
//        val orderList: List<Orders> = orderIPage.records;
////        orderList.forEach { println(it.toString())}
//        println(orderList.size)
//        return orderList
////        Page<Orders>() orderPage = new Page<>(curPage,size);
//    }
}