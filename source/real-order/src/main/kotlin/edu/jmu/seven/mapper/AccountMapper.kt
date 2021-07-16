package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Account
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/18:09
 */

@Mapper
interface AccountMapper : BaseMapper<Account> {

    @Select("select * from account where username = #{username,jdbcType=VARCHAR}")
    fun selectByName(@Param(value = "username") id: String) : Account
}