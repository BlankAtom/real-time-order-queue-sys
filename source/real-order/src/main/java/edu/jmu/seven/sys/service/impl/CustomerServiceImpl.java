package edu.jmu.seven.sys.service.impl;

import edu.jmu.seven.sys.entity.Customer;
import edu.jmu.seven.sys.mapper.CustomerMapper;
import edu.jmu.seven.sys.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
