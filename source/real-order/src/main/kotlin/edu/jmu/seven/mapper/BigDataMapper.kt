package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.BigDataEntity
import org.apache.ibatis.annotations.Mapper

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/08/02/20:15
 */

@Mapper
interface BigDataMapper : BaseMapper<BigDataEntity> {
}