package edu.jmu.seven.controller

import edu.jmu.seven.entity.Dish
import edu.jmu.seven.mapper.DishMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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

//    @GetMapping("/findAll")
//    open fun findAll( ) : List<Dish>{
////        println(dishMapper.findAll());
//
////        return dishMapper.findAll()
//    }


}