package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.Query
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.service.MerchantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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
    private lateinit var mmapper:MerchantMapper
    @Autowired
    private lateinit var accountMapper: AccountMapper
    /**
     * 展示所有商家
     */
    @RequestMapping("/findAllMerchant")
    fun showMerchant(): List<Merchant> {
        val merchants:List<Merchant> =mmapper.selectList(null)
//        merchants.forEach{
//            val license = it.m_license.split('_')
//            it.m_license=license[1]
//            }
        return merchants
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
        return if(accountMapper.delete(accountWrapper)===1&&accountMapper.delete(accountWrapper)===1){
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
    @RequestMapping("/onemerchant")
    fun oneMerchant(m_id:String): Merchant {
        return mmapper.selectById(m_id)
    }

}