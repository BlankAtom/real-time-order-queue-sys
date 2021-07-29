package edu.jmu.seven.service.impl

import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.service.MerchantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/13:30
 */

@Service
class MerchantServiceImpl : MerchantService {



    @Autowired
    lateinit var merchantMapper: MerchantMapper

    override fun showmerchant(): List<Merchant> {
        return merchantMapper.selectAll()
    }

    override fun onemerchant(m_id: String): Merchant {
        return  merchantMapper.selectById(m_id)
    }

}