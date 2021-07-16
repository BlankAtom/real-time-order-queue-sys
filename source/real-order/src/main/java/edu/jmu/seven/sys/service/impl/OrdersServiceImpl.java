package edu.jmu.seven.sys.service.impl;

import edu.jmu.seven.sys.entity.Orders;
import edu.jmu.seven.sys.mapper.OrdersMapper;
import edu.jmu.seven.sys.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seven
 * @since 2021-07-16
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
