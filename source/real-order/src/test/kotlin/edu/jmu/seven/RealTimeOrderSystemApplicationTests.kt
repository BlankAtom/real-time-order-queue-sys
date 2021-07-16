package edu.jmu.seven

import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class RealTimeOrderSystemApplicationTests {

    @Test
    fun contextLoads() {
        // 全局配置
        val projectPath = System.getProperty("user.dir")
        val gc = GlobalConfig.Builder().outputDir("$projectPath/src/main/java")
            .author("Seven").openDir(false).enableSwagger().build()

        // 数据源配置
        val dsc = DataSourceConfig.Builder(
            "jdbc:mariadb://159.75.90.116:3306/vocation",
            "seven",
            "12345678"
        ).schema("vocation").build()


        // 包配置
        val pc = PackageConfig.Builder("edu.jmu.seven", "sys").build()

        // 自定义配置
        val cfg: InjectionConfig = object : InjectionConfig() {
            fun initMap() {
                // to do nothing
            }
        }

        // 策略配置

        // 策略配置
        val strategy = StrategyConfig.Builder().addInclude(
            "account", "cuisine", "customer", "dish", "merchant", "oder_dish", "orders"
        ).build()

        // 代码生成器
        val mpg = AutoGenerator(dsc).global(gc).packageInfo(pc).strategy(strategy)
        mpg.execute()
    }

}
