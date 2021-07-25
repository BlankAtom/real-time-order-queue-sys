package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.entity.Orders
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.service.MerchantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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

}