package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.mapper.OrdersMapper
import edu.jmu.seven.socket.WebSocketServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/10:28
 */
@RestController
@RequestMapping("/api/merchant")
class MerchantController {

    @Autowired
    private lateinit var mmapper: MerchantMapper

    @Autowired
    private lateinit var ordersMapper: OrdersMapper



    @RequestMapping("/call")
    fun callCustomer(@RequestParam("m_id") mid: String, @RequestParam("c_id") cid: String) : String {
        // 呼叫用户
        WebSocketServer.sendMessageTo("123", cid)
        return "123"
    }
    /**
     * 展示所有商家
     */
    @RequestMapping("/showmerchant")
    fun showMerchant(): List<Merchant> {
        return mmapper.selectAll()
    }

    @RequestMapping("/onemerchant")
    fun oneMerchant(@RequestParam("m_id") m_id: String)
            : Merchant {
        return mmapper.selectById(m_id)
    }

    /**
     * 分页返回商家结果
     * @param curPage 页码
     * @param pageCount 每页的内容
     * @param key m_name商家名字
     */
    @RequestMapping("/merchantByPage")
    fun merchantByPage(
            @RequestParam("curPage") curPage: String,
            @RequestParam("pageCount") pageCount: String,
            @RequestParam("key") key: String
    ): List<Merchant> {
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val mPage = Page<Merchant>(curPageLong, pageCountLong)
        return if (key == "") {
            mmapper.selectPage(mPage, null).records
        } else {
            val mWrapper: QueryWrapper<Merchant>? = QueryWrapper<Merchant>().like("m_name", key)
            mmapper.selectPage(mPage, mWrapper).records
        }
    }

    /**
     * 获取指定商家编号的商家
     */
    @RequestMapping("/findMerchantByKey")
    fun findMerchantByKey(
            @RequestParam("key") key: String
    ): List<Merchant> {
        val mWrapper: QueryWrapper<Merchant>? = QueryWrapper<Merchant>().like("m_name", key)
        return mmapper.selectList(mWrapper)
    }

    @RequestMapping("/findMyPosition")
    fun findMyPosition(
            @RequestParam("c_id") c_id: String
    ): Int {
        val m_owrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("c_id", c_id).eq("status", "opened")
        val m_o = ordersMapper.selectList(m_owrapper)
        val m_id = m_o[0].m_id
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("m_id", m_id).eq("status", "opened").orderByAsc("o_start_time")
        var nub = 1
        for (i in ordersMapper.selectList(wrapper)) {
            if (i.c_id == c_id)
                return nub
            nub++
        }
        return 0
    }

    /**
     * 获取m_id为传入参数的所有orders的总数
     * @param c_id
     */
    @RequestMapping("/findNumber")
    fun findNumber(
            @RequestParam("c_id") c_id: String,
    ): Int {
        val m_owrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("c_id", c_id).eq("status", "opened")
        val m_o = ordersMapper.selectList(m_owrapper)
        val m_id = m_o[0].m_id
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("m_id", m_id).orderByAsc("o_start_time")
        var nub = 1
        for (i in ordersMapper.selectList(wrapper)) {
            if (i.c_id == c_id && i.status=="opened") {
                return nub
            }
            nub++
        }
        return 0
    }

    @RequestMapping("/findMyQueue")
    fun findMyQueue(
            @RequestParam("c_id") c_id: String
    ): String {
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("c_id", c_id).eq("status", "opened")
        val m_o = ordersMapper.selectList(wrapper)
        if (m_o.isEmpty())
            return ""

        print(m_o[0].m_id)
        return mmapper.selectByName(m_o[0].m_id)?.m_name ?: ""
//        val mWrapper: QueryWrapper<Merchant>? = QueryWrapper<Merchant>().eq("m_id", m_o[0].m_id)
//        val m = mmapper.selectList(mWrapper)
//        return m
    }

    @RequestMapping("/lineUp")
    fun lineUp(
        @RequestParam("m_id") m_id: String,
        @RequestParam("c_id") c_id: String
    ): Int {
        // 建立订单为打开状态，商家号为指定编号的搜索器
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("m_id", m_id).eq("status","opened")

        val orders = Orders(System.currentTimeMillis().toString(),
            m_id,c_id,9.9, LocalDateTime.now(), LocalDateTime.now(),
            0,"opened",1)

        // 搜索全部的订单+搜索器结果的长度
        val count = ordersMapper.selectList(wrapper).size

        // 插入这个订单
        ordersMapper.insert(orders)

        return count
    }

    @RequestMapping("/isQueuing")
    fun isQueuing(
            @RequestParam("c_id") c_id: String
    ): Int {
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("c_id", c_id).eq("status", "opened")
        val m_o = ordersMapper.selectList(wrapper)
        if (m_o.isEmpty())
            return 0
        return 1
    }

    @RequestMapping("/cancel")
    fun cancel(
            @RequestParam("c_id") c_id: String,
    ):Int {
        if(ordersMapper.updateOrderStatus(c_id)==1)
            return 1
        return 0
    }

}