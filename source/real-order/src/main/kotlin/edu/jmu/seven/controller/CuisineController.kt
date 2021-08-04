package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Cuisine
import edu.jmu.seven.mapper.CuisineMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/cuisine")
class CuisineController {
    @Autowired
    private lateinit var cuisineMapper: CuisineMapper

    /**
     * 查询
     * 所有cuisine
     */
    @RequestMapping("/findAllCuisine")
    fun showCuisine():List<Cuisine>{
        return cuisineMapper.selectList(null)
    }
    /**
     * 查询
     * 一页的cuisine
     */
    @RequestMapping("/findPageCuisine")
    fun showPageCuisine(
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ):List<Cuisine>{
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val cuisinePage = Page<Cuisine>(curPageLong,pageCountLong)
        return cuisineMapper.selectPage(cuisinePage,null).records
    }
    /**
     * 查询
     * 通过name查询cuisine
     */
    @RequestMapping("/findAllCuisineByName")
    fun showCuisineByName(
        @RequestParam("searchName") name:String
    ):List<Cuisine>{
        val cuisineWrapper = QueryWrapper<Cuisine>().like("name",name)
        return cuisineMapper.selectList(cuisineWrapper)
    }
    /**
     * 查询
     * 通过name输出一页的cuisine
     */
    @RequestMapping("/findPageCuisineByName")
    fun showPageByName(
        @RequestParam("searchName") name:String,
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ):List<Cuisine>{
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val cuisinePage = Page<Cuisine>(curPageLong,pageCountLong)
        val cuisineWrapper = QueryWrapper<Cuisine>().like("name",name)
        return cuisineMapper.selectPage(cuisinePage,cuisineWrapper).records
    }

    /**
     * 创建
     * 新建一个菜系
     */
    @RequestMapping("/createCuisine")
    fun createCuisine(
        @RequestParam("cname") name: String
    ):Int{
        val cuisine = Cuisine(name)
        println(cuisine.toString())
        return cuisineMapper.insert(cuisine)
    }
    @RequestMapping("/updateCuisine")
    fun updateCuisine(
        @RequestParam("code") code: String,
        @RequestParam("cname") name: String
    ):Int{
        val cuisine = Cuisine(name)
        val cuisineWrapper = QueryWrapper<Cuisine>().eq("code",code)
        return cuisineMapper.update(cuisine,cuisineWrapper)
    }
    /**
     * 删除
     * 通过code删除菜系
     */
    @RequestMapping("/deleteCuisineByCode")
    fun deleteCuisine(
        @RequestParam("code") code:String
    ):Int{
        val cuisineWrapper = QueryWrapper<Cuisine>().eq("code",code)
        return cuisineMapper.delete(cuisineWrapper)
    }
}