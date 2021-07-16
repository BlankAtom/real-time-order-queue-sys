package edu.jmu.seven.sys.service.impl;

import edu.jmu.seven.sys.entity.Cuisine;
import edu.jmu.seven.sys.mapper.CuisineMapper;
import edu.jmu.seven.sys.service.ICuisineService;
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
public class CuisineServiceImpl extends ServiceImpl<CuisineMapper, Cuisine> implements ICuisineService {

}
