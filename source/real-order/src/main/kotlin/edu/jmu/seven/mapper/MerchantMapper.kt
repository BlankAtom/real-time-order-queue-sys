package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Customer
import edu.jmu.seven.entity.Merchant
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/19/10:21
 */
@Mapper
interface MerchantMapper : BaseMapper<Merchant> {
    @Select("select * from merchant where m_id = #{uname}")
    fun selectByName(@Param(value = "uname") id: String) : Merchant?
}