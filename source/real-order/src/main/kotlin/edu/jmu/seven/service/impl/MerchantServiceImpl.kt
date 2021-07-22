package edu.jmu.seven.service.impl

import edu.jmu.seven.entity.Merchant
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.MerchantMapper
import edu.jmu.seven.service.LoginService
import edu.jmu.seven.service.MerchantService
import edu.jmu.seven.service.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.web.bind.annotation.RequestMapping
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

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