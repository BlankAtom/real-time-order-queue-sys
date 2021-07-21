package edu.jmu.seven.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.seven.entity.Orders;
import edu.jmu.seven.entity.Calling;
import edu.jmu.seven.mapper.CustomerMapper;
import edu.jmu.seven.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController

public class CallingController {

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    CustomerMapper customerMapper;

    @GetMapping("/calling/findAll/{f}/{size}")
    public List<Calling> findAll(@PathVariable("f") int f, @PathVariable("size") int size) {

        List<Calling> callings = new ArrayList<Calling>();
        Page<Orders> page = new Page<>(f,size);
        ordersMapper.selectPage(page,null);
        for(Orders p : page.getRecords()){
            Duration duration = Duration.between(p.getO_start_time(), LocalDateTime.now());
            long time = duration.toMinutes();
            String phone = customerMapper.selectByName(p.getC_id()).getC_phone();
            String p1 = phone.substring(0,3);
            String p2 = phone.substring(7,11);
            phone = p1 + "****" + p2;
            System.out.println(p1);
            Calling calling = new Calling(p.getO_id(),p.getC_id(),
                    phone,p.getO_start_time(), time);
            callings.add(calling);
        }
        return callings;
    }

}
