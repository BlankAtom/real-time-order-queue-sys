package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Dish
import edu.jmu.seven.mapper.DishMapper
import io.lettuce.core.internal.LettuceStrings.toDouble
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/dish")
public  class DishController {
    @Autowired
    private lateinit var dishMapper : DishMapper


    @GetMapping("/findAll")
    open fun findAll( ) : List<Dish>{
//        println(dishMapper.findAll());

        return dishMapper.findAll()
    }

    @RequestMapping("/findByid")
    open fun findByid( @RequestParam("merchant_now") m_id: String ) : List<Dish>{
//        println(dishMapper.findAll());
        var dishwarapper = QueryWrapper<Dish>().eq("m_id",m_id)
        return dishMapper.selectList(dishwarapper)
    }


    @RequestMapping("/insertDish")
    open fun insert(@RequestParam("d_name") d_name: String,
                    @RequestParam("d_id") d_id: String,
                    @RequestParam("m_id") m_id: String,
                    @RequestParam("d_price") d_price: String,
                    @RequestParam("d_pic") d_pic: String,
                    @RequestParam("d_note") d_note: String,
                    @RequestParam("d_sum") d_sum: String,
                    @RequestParam("cuision_code")  cuision_code: String
                    )
    {

                var double_price=d_price.toDouble()
                var int_sum=d_sum.toInt()
                println("增加"+d_name+"___"+d_id+"___"+m_id+"___"+
                        d_price+"___"+d_pic+"___"+d_note+
                        "___"+d_sum+"___"+cuision_code)
                        var Add_dish=Dish(d_id,d_name,m_id,double_price,d_pic,d_note,int_sum,cuision_code)
                        dishMapper.insert(Add_dish)

    }


    @RequestMapping("/updateDish")
   fun update(
      @RequestParam("d_name") d_name: String,
      @RequestParam("d_id") d_id: String,
      @RequestParam("m_id") m_id: String,
      @RequestParam("d_price") d_price: String,
      @RequestParam("d_pic") d_pic: String,
      @RequestParam("d_note") d_note: String,
      @RequestParam("d_sum") d_sum: String,
      @RequestParam("cuision_code")  cuision_code: String
    ){
        var double_price=d_price.toDouble()
        var int_sum=d_sum.toInt()
//        println("更新"+d_name+"___"+d_id+"___"+m_id+"___"+
//                d_price+"___"+d_pic+"___"+d_note+
//                "___"+d_sum+"___"+cuision_code)
        var upda_dish=Dish(d_id,d_name,m_id,double_price,d_pic,d_note,int_sum,cuision_code)
        //  dishMapper.insert(Add_dish)
        dishMapper.updateById(upda_dish)

    }

    @RequestMapping("/deleteDish")
    fun delete(
        @RequestParam("dish_id") d_id: String
    ){
        println("删除 "+d_id)
        var dishwarapper = QueryWrapper<Dish>().eq("d_id",d_id)
        dishMapper.delete(dishwarapper)
//        println(dishMapper.selectList(dishwarapper))
        //  dishMapper.insert(Add_dish)

    }
    @RequestMapping("/findPage")
    fun findPageDish(
        @RequestParam("current_page") current_page: String,
        @RequestParam("size_page") size_page: String,
        @RequestParam("m_id") m_id: String

    ):List<Dish>{
        var int_page=current_page.toLong()
        var int_size=size_page.toLong()
        var dishPage: Page<Dish> = Page<Dish>(int_page, int_size);

        val dishwrapper = QueryWrapper<Dish>().eq("m_id", m_id)
        var dishIPage: IPage<Dish> = dishMapper.selectPage(dishPage,dishwrapper)
        val dishList: List<Dish> =dishIPage.records;
//        orderList.forEach { println(it.toString())}
//        dishList.forEach { println(it.toString()) }
        return dishList
//        Page<Orders>() orderPage = new Page<>(curPage,size);
    }
}