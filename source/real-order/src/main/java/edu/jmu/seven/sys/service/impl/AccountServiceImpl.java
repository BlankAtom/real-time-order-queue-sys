package edu.jmu.seven.sys.service.impl;

import edu.jmu.seven.sys.entity.Account;
import edu.jmu.seven.sys.mapper.AccountMapper;
import edu.jmu.seven.sys.service.IAccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
