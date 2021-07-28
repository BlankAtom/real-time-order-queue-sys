package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.OrdersMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/data")
class OrdersController {

    @Autowired
    lateinit var omapper: OrdersMapper

    /**
     * 查询所有订单
     * @param m_id 商家的ID
     */
    @RequestMapping("/findAll")
    fun selectOrders(
        @RequestParam("m_id") m_id: String
    ): List<Orders> {
        val orderwrapper = QueryWrapper<Orders>().eq("m_id", m_id)
        return omapper.selectList(orderwrapper)
    }
    /**
     *查询一页的order
     * @param m_id 商家ID
     * @param curPage 页码
     * @param pageCount 一页显示的数量
     */
    @RequestMapping("/findPage")
    fun selectPageOrders(
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


    /**
     *  通过输入o_id模糊查询
     *  @param m_id 商家id
     *  @param searchId o_id或者c_id
     *  @param choose 1：o_id 2:c_id
     */
    @RequestMapping("/findAllById")
    fun selectOrderById(
        @RequestParam("m_id") m_id: String,
        @RequestParam("searchId") searchId: String,
        @RequestParam("choose") choose: String
    ): List<Orders>? {
//        println("$searchId $m_id")
        var orderWrapper: QueryWrapper<Orders> = if(choose == "1"){
            QueryWrapper<Orders>().eq("m_id",m_id).like("o_id",searchId)
        } else {
            QueryWrapper<Orders>().eq("m_id",m_id).like("c_id",searchId)
        }

//        println(omapper.selectList(orderWrapper))
        return omapper.selectList(orderWrapper)
    }
    /**
     *  通过输入o_id模糊查询当前页的order
     *  @param m_id 商家id
     *  @param searchId o_id或者c_id
     *  @param choose 1：o_id 2:c_id
     *  @param curPage 页码
     *  @param pageCount 一页显示的数量
     */
    @RequestMapping("/findPageById")
    fun selectPageById(
        @RequestParam("m_id") m_id: String,
        @RequestParam("searchId") searchId: String,
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String,
        @RequestParam("choose") choose: String
    ): List<Orders>? {
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val orderPage = Page<Orders>(curPageLong,pageCountLong);
        val orderWrapper: QueryWrapper<Orders> = if(choose == "1"){
            QueryWrapper<Orders>().eq("m_id",m_id).like("o_id",searchId)
        } else {
            QueryWrapper<Orders>().eq("m_id",m_id).like("c_id",searchId)
        }
        return omapper.selectPage(orderPage,orderWrapper).records
    }
    /**
     * 通过订单ID删除
     * @param o_id 订单ID
     */
    @RequestMapping("/deleteOrderByo_id")
    fun deleteOrder(
        @RequestParam("o_id") o_id:String
    ){
        val orderwrapper = QueryWrapper<Orders>().eq("o_id",o_id)
        omapper.delete(orderwrapper)
    }

}