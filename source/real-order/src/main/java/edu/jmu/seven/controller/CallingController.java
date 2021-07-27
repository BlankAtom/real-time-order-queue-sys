package edu.jmu.seven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.seven.entity.Dish;
import edu.jmu.seven.entity.Order;
import edu.jmu.seven.entity.Orders;
import edu.jmu.seven.entity.Calling;
import edu.jmu.seven.mapper.CustomerMapper;
import edu.jmu.seven.mapper.DishMapper;
import edu.jmu.seven.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController

public class CallingController {

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    DishMapper dishMapper;

    @GetMapping("/calling/findAll/{f}/{size}/{id}")
    public List<Calling> findAll(@PathVariable("f") int f, @PathVariable("size") int size,@PathVariable("id") String id) {

        List<Calling> callings = new ArrayList<Calling>();
        Page<Orders> page = new Page<>(f,size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>().eq
                ("status","opened").orderByAsc
                ("o_start_time").eq("m_id",id);
        ordersMapper.selectPage(page,wrapper);
        int total = ordersMapper.selectList(wrapper).size();
//        System.out.println(ordersMapper.selectList(null));
        for(Orders p : page.getRecords()){
            Duration duration = Duration.between(p.getO_start_time(), LocalDateTime.now());
            long time = duration.toMinutes();
            String phone = customerMapper.selectByName(p.getC_id()).getC_phone();
            String p1 = phone.substring(0,3);
            String p2 = phone.substring(7,11);
            phone = p1 + "****" + p2;
            Calling calling = new Calling(p.getO_id(),p.getC_id(),
                    phone,p.getO_start_time(), time,total);
            callings.add(calling);
        }
        return callings;
    }

    @GetMapping("/calling/closeOrder/{id}")
    public String closeOrder(@PathVariable("id") String id){
//        System.out.println("1111");
        if(ordersMapper.updateStatusById(id)==1){
            return "success";
        }
        return "false";
    }

//    @GetMapping("/caling/getOrderTotal/{id}")
//    public List<Order> getOrederTotal(@PathVariable("id") String id){
//        List<Order> orders = new ArrayList<>();
//        QueryWrapper<Dish> wrapper = new QueryWrapper<Dish>().eq("m_id",id);
//        List<Dish> dishes = dishMapper.selectList(wrapper);
//        for(Dish d: dishes){
//            Order order = new Order(d.getD_name(),d.getD_price(),dishes.size());
//            orders.add(order);
//        }
//        return orders;
//    }

//    @GetMapping("/calling/findOrder/{f}/{size}/{id}")
    @GetMapping("/calling/findOrder/{id}")
    public List<Order> findOrder(@PathVariable("id") String id){
        List<Order> orders = new ArrayList<>();
//        Page<Dish> page = new Page<>(f,size);
        QueryWrapper<Dish> wrapper = new QueryWrapper<Dish>().eq("m_id",id);
//        dishMapper.selectPage(page,wrapper);
        List<Dish> dishes = dishMapper.selectList(wrapper);
        for(Dish d: dishes){
            Order order = new Order(d.getD_name(),d.getD_price(),0);
            orders.add(order);
        }
//        int total = dishMapper.selectList(null).size();
//        for(Dish d: page.getRecords()){
//            Order order = new Order(d.getD_name(),d.getD_price(),0,total);
//            orders.add(order);
//        }
        return orders;

    }

    @GetMapping("/calling/updateOrder/{id}/{type}/{cost}")
    public String updateOrder(@PathVariable("id") String id,@PathVariable("cost") Double cost,@PathVariable("type") int type){
        if(ordersMapper.updateOrderById(cost,type,id,LocalDateTime.now())==1){
            return "success";
        }
        return "false";
    }

}

