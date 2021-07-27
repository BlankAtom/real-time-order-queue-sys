package edu.jmu.seven.config

import org.apache.spark.SparkContext
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/27/15:11
 */

@Configuration
class SparkConfig {
    companion object {
        /**
         * App name, 不影响使用
         */
        const val appName = "RealOrderSpark"

        /**
         * master的地址，看情况更改
         */
        const val master = "local"

        /**
         * isMaster的布尔值，一般用不到
         */
//        const val isMaster = false

        /**
         * 创建javaSparkContext
         */
//        @Bean
        fun javaSparkContext() : JavaSparkContext {
            return JavaSparkContext.fromSparkContext(SparkContext(master, appName))
        }
    }


}