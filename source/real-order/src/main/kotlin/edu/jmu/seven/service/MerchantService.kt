package edu.jmu.seven.service

import edu.jmu.seven.entity.Merchant

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/13:27
 */
interface MerchantService {
    fun showmerchant() : List<Merchant>

    fun onemerchant(m_id:String):Merchant
}