package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Orders
import org.apache.ibatis.annotations.Mapper

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/20/19:50
 */
@Mapper
interface OrderMapper : BaseMapper<Orders>{
}