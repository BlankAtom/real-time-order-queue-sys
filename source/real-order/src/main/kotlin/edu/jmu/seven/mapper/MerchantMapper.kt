package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.Merchant
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/18:09
 */

@Mapper
interface MerchantMapper : BaseMapper<Merchant> {

    @Select("select * from merchant")
    fun selectAll() : List<Merchant>
}