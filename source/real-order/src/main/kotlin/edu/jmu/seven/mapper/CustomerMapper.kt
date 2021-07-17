package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.Customer
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/14:55
 */

@Mapper
interface CustomerMapper : BaseMapper<Customer> {

    @Select("select * from customer where c_id = #{uname}")
    fun selectByName(@Param(value = "uname") id: String) : Customer
}