package edu.jmu.seven.controller

import edu.jmu.seven.entity.Dish
import edu.jmu.seven.mapper.DishMapper
import io.lettuce.core.internal.LettuceStrings.toDouble
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/dish")
public  class DishController {
    @Autowired
    private lateinit var dishMapper : DishMapper
//    @GetMapping("/findAll/{page}/{size}")
//    open fun findAll(@PathVariable("page")  page:Int, @PathVariable("size") size:Int ) : List<Dish>{
////        println(dishMapper.findAll());
//
//        val request :PageRequest= PageRequest.of(page, size)
//        return dishMapper.findAll(request)
//    }

    @GetMapping("/findAll")
    open fun findAll( ) : List<Dish>{
//        println(dishMapper.findAll());

        return dishMapper.findAll()
    }
//    @GetMapping("/findPage")
//    open  fun  findPage(@PathVariable("page")  page:Int, @PathVariable("size")  size:Int): Page<Dish> {
//        val request :PageRequest= PageRequest.of(page, size)
//        return
//    }
////    @PutMapping("/insertDish")
//    open fun insert(str: String):String {
//
//        ///println(44444444444444444444444444444444)
//        return str
//        //return dishMapper.insert(dish)
//    }
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
                println(d_name+"___"+d_id+"___"+m_id+"___"+
                        d_price+"___"+d_pic+"___"+d_note+
                        "___"+d_sum+"___"+cuision_code)
                        var Add_dish=Dish(d_id,d_name,m_id,double_price,d_pic,d_note,int_sum,cuision_code)
                      //  dishMapper.insert(Add_dish)

    }
}