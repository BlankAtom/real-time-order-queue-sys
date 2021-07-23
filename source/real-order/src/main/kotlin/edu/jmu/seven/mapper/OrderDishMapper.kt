package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.OrderDish
import org.apache.ibatis.annotations.Mapper

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/22/17:20
 */

@Mapper
interface OrderDishMapper : BaseMapper<OrderDish> {
}