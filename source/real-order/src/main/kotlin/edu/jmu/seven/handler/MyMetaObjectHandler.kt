package edu.jmu.seven.handler

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * <p>这个类的作用是实现 Mybatis-plus 的字段 Fill 策略</p>
 * <p>这里主要是对 CreateTime 和 UpdateTime 进行填充</p>
 * @author github/blackswords
 * @date 2021/07/17/0:01
 */

@Component
class MyMetaObjectHandler : MetaObjectHandler {
    override fun insertFill(metaObject: MetaObject?) {
//        Log
        this.setFieldValByName("created_at", LocalDateTime.now(), metaObject)
        this.setFieldValByName("updated_at", LocalDateTime.now(), metaObject)

    }

    override fun updateFill(metaObject: MetaObject?) {
        this.setFieldValByName("updated_at", LocalDateTime.now(), metaObject)

    }
}