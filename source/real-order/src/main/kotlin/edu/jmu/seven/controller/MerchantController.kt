package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.entity.Merchant_Order
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.mapper.OrdersMapper
import edu.jmu.seven.service.MerchantService
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
@RequestMapping("/cus")
class MerchantController {

    @Autowired
    private lateinit var mmapper: MerchantMapper

    @Autowired
    private lateinit var ordersMapper: OrdersMapper

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

    @RequestMapping("/merchantByPage")
    fun merchantByPage(
            @RequestParam("curPage") curPage: String,
            @RequestParam("pageCount") pageCount: String,
            @RequestParam("key") key: String
    ): List<Merchant> {
        if (key == "") {
            val curPageLong = curPage.toLong()
            val pageCountLong = pageCount.toLong()
            var mPage = Page<Merchant>(curPageLong, pageCountLong)
            mPage = mmapper.selectPage(mPage, null)
            val mList: List<Merchant> = mPage.records;
            return mList
        } else {
            val curPageLong = curPage.toLong()
            val pageCountLong = pageCount.toLong()
            var mPage = Page<Merchant>(curPageLong, pageCountLong)
            val mWrapper: QueryWrapper<Merchant>? = QueryWrapper<Merchant>().like("m_name", key)
            mPage = mmapper.selectPage(mPage, mWrapper)
            val mList: List<Merchant> = mPage.records
            return mList
        }
    }

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
            if (i.c_id == c_id)
                return nub
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
    ) {
        print(m_id)
        val orders: Orders = Orders(System.currentTimeMillis().toString(),
                m_id, c_id, 9.9, LocalDateTime.now(), LocalDateTime.now(),
                0, "opened", 1)
        ordersMapper.insert(orders)
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