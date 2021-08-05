package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.mapper.OrdersMapper
import edu.jmu.seven.socket.WebSocketServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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

    @Autowired
    private lateinit var accountMapper: AccountMapper

    /**
     * 展示所有商家
     */
    @RequestMapping("/findAllMerchant")
    fun showMerchant(): List<Merchant> {
        //        merchants.forEach{
//            val license = it.m_license.split('_')
//            it.m_license=license[1]
//            }
        return mmapper.selectList(null)
    }

    /**
     * 查询一页商家账号
     * @param curPage 当前页数
     * @param pageCount 每一页显示的数量
     */
    @RequestMapping("/findPageMerchant")
    fun showPageMerchant(
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ):List<Merchant>{
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val merchantPage = Page<Merchant>(curPageLong,pageCountLong)
        val merchants:List<Merchant> = mmapper.selectPage(merchantPage,null).records
        merchants.forEach{
            val license = it.m_license.split('_')
            it.m_license=license[1]
        }
        return merchants
    }

    /**
     * 插入account和merchant表
     */
    @RequestMapping("/insert")
    fun insertMerchant(
        @RequestParam("phone") phone:String,
        @RequestParam("psw") psw:String,
        @RequestParam("name") name:String,
        @RequestParam("license") license:String,
        @RequestParam("address") address:String,
        @RequestParam("openTime") openTime:String,
        @RequestParam("closeTime") closeTime:String,
        @RequestParam("tag") tag:String,
        @RequestParam("notice") notice:String,
        @RequestParam("icon") icon:String,
    ): Int {
        val encoder = BCryptPasswordEncoder()
        val newPass = encoder.encode(psw)
        val account = Account("merchant_$phone",newPass,2)
        val merchant = Merchant("merchant_$phone",name,"liensemerchant_$license",phone,address,openTime,closeTime,tag,notice,icon)
        accountMapper.insert(account)
        mmapper.insert(merchant)
        if(accountMapper.insert(account)==1&&mmapper.insert(merchant)==1){
            return 1
        }else {
            return 0
        }
//        println(merchant.toString())
//        println(account.toString())
    }

    /**
     * 修改对应m_id的商家信息
     */
    @RequestMapping("/updateMerchant")
    fun updateMerchantBym_id(
        @RequestParam("m_id") m_id: String,
        @RequestParam("phone") phone: String,
        @RequestParam("name") name:String,
        @RequestParam("license") license:String,
        @RequestParam("address") address:String,
        @RequestParam("openTime") openTime:String,
        @RequestParam("closeTime") closeTime:String,
        @RequestParam("tag") tag:String,
        @RequestParam("notice") notice:String,
        @RequestParam("icon") icon:String,
    ): Int {
        val merchantWrapper = QueryWrapper<Merchant>().eq("m_id",m_id)
        val merchant = Merchant(m_id,name,"liensemerchant_$license",phone,address,openTime,closeTime,tag,notice,"")
        return mmapper.update(merchant,merchantWrapper)
    }


    /**
     * 通过m_id删除
     */
    @RequestMapping("/deleteMerchantBym_id")
    fun deleteMerchantBym_id(
        @RequestParam("m_id") m_id: String
    ): Int {
        val merchantWrapper = QueryWrapper<Merchant>().eq("m_id",m_id)
        val accountWrapper = QueryWrapper<Account>().eq("username",m_id)
        mmapper.delete(merchantWrapper)
        return if(accountMapper.delete(accountWrapper)==1&&accountMapper.delete(accountWrapper)==1){
            1
        }else
            0
    }
    /**
     * 根据名字查询所有商家
     */
    @RequestMapping("/findAllBym_name")
    fun selectAllBym_name(
        @RequestParam("m_name") m_name:String
    ):List<Merchant>{
        val merchantWrapper = QueryWrapper<Merchant>().like("m_name",m_name)
        return mmapper.selectList(merchantWrapper)
    }

    /**
     * 根据名字查询一页商家
     */
    @RequestMapping("/findPageBym_name")
    fun selectPageBym_name(
        @RequestParam("m_name") m_name: String,
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ):List<Merchant>{
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val merchantPage = Page<Merchant>(curPageLong,pageCountLong)
        val merchantWrapper = QueryWrapper<Merchant>().like("m_name",m_name)
        val merchants:List<Merchant> = mmapper.selectPage(merchantPage,merchantWrapper).records
        merchants.forEach{
            val license = it.m_license.split('_')
            it.m_license=license[1]
        }
        return merchants
    }

    @RequestMapping("/call")
    fun callCustomer(@RequestParam("m_id") mid: String, @RequestParam("phone") cid: String) : String {
        // 呼叫用户
//        WebSocketServer.sendMessageTo("123", cid)
        return "123"
    }
//    /**
//     * 展示所有商家
//     */
//    @RequestMapping("/showmerchant")
//    fun showMerchant(): List<Merchant> {
//        return mmapper.selectAll()
//    }

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
        val m_o = ordersMapper.selectList(m_owrapper) ?: return 0
        val m_id = m_o[0].m_id
        val wrapper: QueryWrapper<Orders>? = QueryWrapper<Orders>().eq("m_id", m_id).eq("status", "opened").orderByAsc("o_start_time")
        var nub = 1
        val odm = ordersMapper.selectList(wrapper) ?: return 0
        for (i in odm) {
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