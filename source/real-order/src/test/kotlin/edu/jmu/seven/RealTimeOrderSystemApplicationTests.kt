package edu.jmu.seven

import edu.jmu.seven.entity.Account
import edu.jmu.seven.entity.Customer
import edu.jmu.seven.entity.Dish
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.mapper.CustomerMapper
import edu.jmu.seven.mapper.OrderMapper
import edu.jmu.seven.utils.SHAUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import kotlin.random.Random


@SpringBootTest
class RealTimeOrderSystemApplicationTests {

    @Autowired
    lateinit var mapper: AccountMapper

    @Autowired
    lateinit var cmapper: CustomerMapper
    @Test
    fun insert() {
        mapper.insert(Account("user_16655554444", BCryptPasswordEncoder().encode("123456"), 1))
        cmapper.insert(Customer("user_16655554444", "err", "16655554444"))
    }
    @Test
    fun contextLoads() {
        // 对自动填充、逻辑删除、版本号进行数据库测试
        // Select All
        var res = mapper.selectList(null)
        res.forEach { println(it.toString()) }

        // Insert new, And the sha512's length is least 64
        val pwd = SHAUtil().SHA512("123456")
        println("$pwd : ${pwd?.length}")
        mapper.insert(Account("test_123456", pwd!!, 1))

        res = mapper.selectList(null)
        res.forEach { println(it.toString()) }

        mapper.deleteById("test_123456")


        res = mapper.selectList(null)
        res.forEach { println(it.toString()) }
    }

    @Autowired
    lateinit var orderMapper: OrderMapper
    @Test
    fun testOrder() {
        val v = orderMapper.selectList(null)
        v.forEach { println(it) }
    }

    @Test
    fun createData() {
        // dish
        for(s in 1..100) {
            val did = "dish_" + LocalDateTime.now().second + Random.nextInt()
            val dname = "dname_" + Random.nextInt()
            val mid = "merchant_8995566"
            val dPrice = Random.nextInt().toDouble() / 100
            val dPic = "https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/20210418135821.png"
            val dNote = "你希望这是什么呢"
            val dSum = 0
            val cus = ""
            Dish()
        }
    }
}
