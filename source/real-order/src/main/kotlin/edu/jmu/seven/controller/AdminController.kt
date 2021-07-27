package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Account
import edu.jmu.seven.mapper.AccountMapper
import edu.jmu.seven.spark.MySpark
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/admin")
class AdminController {

    @Autowired
    lateinit var accountMapper: AccountMapper


    /**
     * 显示大数据内容
     */
    @RequestMapping("/bigdata")
    fun getLastDayBigData(@RequestParam("index") i: Int ) : Map<String, Int> {
        val ms = MySpark()
        return when(i) {
            1 -> ms.getNumberWithCount()
            2 -> ms.getMerchantVisited()
            else -> ms.getSearchWordCount()
        }
    }


    /**
     * 查询所有用户
     */
    @RequestMapping("/findAllAccount")
    fun showAllCustomers():List<Account>{
        return  accountMapper.selectList(null)
    }
    /**
     * 查询一页用户
     * @param curPage 当前页数
     * @param pageCount 每一页显示的数量
     */
    @RequestMapping("/findPageAccount")
    fun showPageAccount(
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ): List<Account> {
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val accountPage = Page<Account>(curPageLong, pageCountLong)
        return accountMapper.selectPage(accountPage,null).records
    }


    /**
     * 模糊查询所有用户
     * @param username 传入部分username
     */
    @RequestMapping("/findAllByUsername")
    fun selectAllByUsername(
        @RequestParam("username") username: String,
    ): List<Account> {
        val accountwrapper = QueryWrapper<Account>().like("username", username)
//        println(accountMapper.selectList(accountwrapper).size)
        return accountMapper.selectList(accountwrapper)
    }

    /**
     * 模糊查询用户
     * @param username 传入的部分username
     * @param curPage 页码
     * @param pageCount 一页显示的数量
     */
    @RequestMapping("/findPageByUsername")
    fun selectPageByUsername(
        @RequestParam("username") username: String,
        @RequestParam("curPage") curPage: String,
        @RequestParam("pageCount") pageCount: String
    ): List<Account> {
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        val accountwrapper = QueryWrapper<Account>().like("username", username)
        val accountPage = Page<Account>(curPageLong, pageCountLong)
        return accountMapper.selectPage(accountPage,accountwrapper).records
    }


    /**
     * 修改对应username的密码
     * @param psw 输入的新密码
     * @param username 输入的账号
     */
    @RequestMapping("/updatePassword")
    fun updatePassword(
        @RequestParam("psw") psw:String,
        @RequestParam("username") username:String
    ):Int{
        val accountwrapper = QueryWrapper<Account>().eq("username",username)
        val account: Account = accountMapper.selectById(username)
        val encoder = BCryptPasswordEncoder()
        val newPass = encoder.encode(psw)
        account.password = newPass
        return accountMapper.update(account,accountwrapper)
    }





//    @RequestMapping("/test")
//    fun showPage(): List<Account> {
//        var accountwrapper = UpdateWrapper<Account>().like("username", "6563")
//        return accountMapper.selectList(accountwrapper)
//    }
}
