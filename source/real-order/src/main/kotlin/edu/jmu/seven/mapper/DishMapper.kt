package edu.jmu.seven.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import edu.jmu.seven.entity.Dish
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.data.domain.PageRequest

@Mapper
interface DishMapper : BaseMapper<Dish> {
    @Select("select * from dish")
    fun findAll(): List<Dish>

}