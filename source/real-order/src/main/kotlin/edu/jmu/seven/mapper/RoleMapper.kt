package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.UserRole
import org.apache.ibatis.annotations.Mapper

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/17:57
 */
@Mapper
interface RoleMapper : BaseMapper<UserRole>{
}