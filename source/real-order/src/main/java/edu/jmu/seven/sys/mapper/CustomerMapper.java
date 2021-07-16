package edu.jmu.seven.sys.mapper;

import edu.jmu.seven.sys.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Seven
 * @since 2021-07-16
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
