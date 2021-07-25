package edu.jmu.seven

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper
import edu.jmu.seven.entity.*
import edu.jmu.seven.mapper.*
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
//        mapper.insert(Account("user_16655554444", BCryptPasswordEncoder().encode("123456"), 1))
//        cmapper.insert(Customer("user_16655554444", "err", "16655554444"))
    }
    @Test
    fun contextLoads() {
        // 对自动填充、逻辑删除、版本号进行数据库测试
        // Select All
//        var res = mapper.selectList(null)
//        res.forEach { println(it.toString()) }
//
//        // Insert new, And the sha512's length is least 64
//        val pwd = SHAUtil().SHA512("123456")
//        println("$pwd : ${pwd?.length}")
//        mapper.insert(Account("test_123456", pwd!!, 1))
//
//        res = mapper.selectList(null)
//        res.forEach { println(it.toString()) }
//
//        mapper.deleteById("test_123456")
//
//
//        res = mapper.selectList(null)
//        res.forEach { println(it.toString()) }
    }

    @Autowired
    lateinit var orderMapper: OrderMapper
    @Test
    fun testOrder() {
//        val v = orderMapper.selectList(null)
//        v.forEach { println(it) }
    }


    @Autowired
    lateinit var dishMapper: DishMapper
    @Autowired
    lateinit var orderDishMapper: OrderDishMapper
    @Autowired
    lateinit var merchantMapper: MerchantMapper
    @Autowired
    lateinit var accountMapper: AccountMapper
    @Autowired
    lateinit var customerMapper: CustomerMapper

    @Test
    fun createData() {
        // Account

//        for(i in 1..100 ) {
//            val phone: String = "199" + Random.nextInt(1000, 9999).toString() + Random.nextInt(1000, 9999).toString()
//            val cus = Customer("user_"+phone, "u"+phone.substring(7, 11), phone)
//            val act = Account("user_" + phone, BCryptPasswordEncoder().encode(phone), 1)
//            customerMapper.insert(cus)
//            accountMapper.insert(act)
//        }
        // dish
//        for(s in 1..100) {
//            val did = "dish_" + LocalDateTime.now().second + Random.nextInt(10000000)
//            val dname = "dname_" + Random.nextInt()
//            val mid = "merchant_8995566"
//            val dPrice = Random.nextInt().toDouble() / 100
//            val dPic = "https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/20210418135821.png"
//            val dNote = "你希望这是什么呢"
//            val dSum = 0
//            val cus = ""
//            val d = Dish(did, dname, mid, dPrice, dPic, dNote, dSum, cus)
//            println(d.toString())
//            dishMapper.insert(d)
//        }


        // Order
//        for(s in 1..100) {
//            val odr = "odr" + Random.nextInt(100000000, 999999999)
//            val mid = "merchant_8995566"
//            val cid = "user_18877776666"
//            val cost = "9999.99".toDouble()
//            val ost = LocalDateTime.now()
//            val opt = LocalDateTime.now()
//            val opty = 0
//            val status = when ( Random.nextInt(1, 3) ) {
//                1 -> "closed"
//                2 -> "opened"
//                3 -> "cancel"
//                else -> "closed"
//            }
//            val odn = Random.nextInt(1, 100)
//            val od = Orders(odr, mid, cid, cost, ost, opt, opty, status, odn)
//            println(od.toString())
//            orderMapper.insert(od)
//        }

        // order dish
//        val ors = orderMapper.selectList(null)
//        val dh = dishMapper.selectList(null)
//        for( o in ors ) {
//            for ( i in 1..Random.nextInt(dh.size) ) {
//                val odid = "od" + Random.nextInt(100000000, 999999999)
//                val od = OrderDish(odid, o.o_id, dh[i].d_id, Random.nextInt(1, 10))
//                orderDishMapper.insert(od)
//            }
//        }

        // merchant 20
//        for( i in 1..20 ) {
//            val mid = "merchant_" + LocalDateTime.now().minute + LocalDateTime.now().second + Random.nextInt(999)
//            val m_name = "name_" + mid
//            val m_liense = "liense" + mid
//            val phone = "199" + Random.nextInt(9999) + Random.nextInt(9999)
//            val maddress = "不知道"
//            val mot = "09:00"
//            val mdt = "18:00"
//            val mtg = "tag"
//            val mnotice = "notice"
//            val micon = "https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/20210418135821.png"
//            val m = Merchant(mid, m_name, m_liense, phone, maddress, mot, mdt, mtg, mnotice, micon)
//            merchantMapper.insert(m)
//        }


//        val list = dishMapper.selectList(null)
//        for(l in list ) {
//                val uw = UpdateWrapper<Dish>()
//                uw.eq("d_id", l.d_id)
//                l.d_price = Random.nextInt(0, 9999).toDouble() / 100
//
//                dishMapper.update(l, uw)
//        }

    }


}
