package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Cuisine
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CuisineMapper:BaseMapper<Cuisine> {
}