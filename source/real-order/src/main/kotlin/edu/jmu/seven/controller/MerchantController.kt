package edu.jmu.seven.controller

import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.service.MerchantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
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

    /**
     * 展示所有商家
     */
    @RequestMapping("/showmerchant")
    fun showMerchant(): List<Merchant> {
        return mmapper.selectAll()
    }

    @RequestMapping("/onemerchant")
    fun oneMerchant(m_id:String): Merchant {
        return mmapper.selectById(m_id)
    }

}