package edu.jmu.seven.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import edu.jmu.seven.entity.Dish
import edu.jmu.seven.mapper.DishMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import kotlin.random.Random


@RestController
@RequestMapping("/dish")
class DishController {
    @Autowired
    private lateinit var dishMapper : DishMapper

    @RequestMapping("/findByid")
    fun findByid(@RequestParam("merchant_now") m_id: String ) : List<Dish>{
//        println(dishMapper.findAll());
        val dishwarapper = QueryWrapper<Dish>().eq("m_id",m_id)
        return dishMapper.selectList(dishwarapper)
    }


    @RequestMapping("/insertDish")
    fun insert(@RequestParam("d_name") d_name: String,
               @RequestParam("d_id") d_id: String,
               @RequestParam("m_id") m_id: String,
               @RequestParam("d_price") d_price: String,
               @RequestParam("d_pic") d_pic: MultipartFile,
               @RequestParam("d_note") d_note: String,
               @RequestParam("d_sum") d_sum: String,
               @RequestParam("cuision_code")  cuision_code: String
    ) {

        val double_price = d_price.toDouble()
        val int_sum = d_sum.toInt()
        println(
            "增加" + d_name + "___" + d_id + "___" + m_id + "___" +
                    d_price + "___" + d_pic + "___" + d_note +
                    "___" + d_sum + "___" + cuision_code
        )
        val fn = d_pic.originalFilename?.lowercase()!!
        val Add_dish = Dish(d_id, d_name, m_id, double_price, fn, d_note, int_sum, cuision_code)
        dishMapper.insert(Add_dish)

    }

    /**
     * 接收单个图片上传
     * 注：upload的默认参数名为“file”，此处使用其他参数名会导致无法获取到传入的内容
     */
    @RequestMapping("/upload/pic")
    fun uploadPic(@RequestParam("file") pic: MultipartFile) : String {

        val fn = pic.originalFilename?.lowercase()
        println(fn)

        // 判断文件类型是否符合要求
        if( !fn!!.endsWith(".bmp") &&
            !fn.endsWith(".jps") &&
            !fn.endsWith(".jpeg") &&
            !fn.endsWith(".png") &&
            !fn.endsWith(".png") &&
            !fn.endsWith(".gif")
        ) {
            // 不符合要求，返回失败结果
            return "{code=\"f001\", msg=\"上传失败\"}"
        }

        // 获得class运行路径
        val path: String = ResourceUtils.getURL("classpath:").path
        // 打开静态上传图片路径
        val fileUploadDir = File(path, "static/images/upload")
        // 建立这个路径
        if( !fileUploadDir.exists() ) {
            fileUploadDir.mkdirs()
        }

        // 给图片生成新的名字
        val ns = fn.substring(fn.lastIndexOf('.') + 1)
        val ran = Random.nextInt(100, 999)
        val ldt = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss_$ran").format(LocalDateTime.now()) + "." + ns

        // 创建图片
        try {
            val picFile = File(fileUploadDir, ldt)
//            logger.info("123")
            pic.transferTo(picFile)
        } catch (e: IOException) {
            e.printStackTrace()
            // 捕捉一个IO异常，这是有可能发生的，打印异常（其实更应该写成日志）并返回内容
            return ""
        }

        // 上传成功，返回一个可解密的加密码，用来方便上传信息全部信息时检索信息（或者使用时间戳+随机数的形式）
        return "{code=\"S001\", msg=\"上传成功\"}"
    }


    @RequestMapping("/updateDish")
   fun update(
      @RequestParam("d_name") d_name: String,
      @RequestParam("d_id") d_id: String,
      @RequestParam("m_id") m_id: String,
      @RequestParam("d_price") d_price: String,
      @RequestParam("d_pic") d_pic: String,
      @RequestParam("d_note") d_note: String,
      @RequestParam("d_sum") d_sum: String,
      @RequestParam("cuision_code")  cuision_code: String
    ){
        var double_price=d_price.toDouble()
        var int_sum=d_sum.toInt()
//        println("更新"+d_name+"___"+d_id+"___"+m_id+"___"+
//                d_price+"___"+d_pic+"___"+d_note+
//                "___"+d_sum+"___"+cuision_code)
        var upda_dish=Dish(d_id,d_name,m_id,double_price,d_pic,d_note,int_sum,cuision_code)
        //  dishMapper.insert(Add_dish)
        dishMapper.updateById(upda_dish)

    }

    @RequestMapping("/deleteDish")
    fun delete(
        @RequestParam("dish_id") d_id: String
    ){
        println("删除 "+d_id)
        var dishwarapper = QueryWrapper<Dish>().eq("d_id",d_id)
        dishMapper.delete(dishwarapper)
//        println(dishMapper.selectList(dishwarapper))
        //  dishMapper.insert(Add_dish)

    }
    @RequestMapping("/findPage")
    fun findPageDish(
        @RequestParam("current_page") current_page: String,
        @RequestParam("size_page") size_page: String,
        @RequestParam("m_id") m_id: String

    ):List<Dish>{
        var int_page=current_page.toLong()
        var int_size=size_page.toLong()
        var dishPage: Page<Dish> = Page<Dish>(int_page, int_size);

        val dishwrapper = QueryWrapper<Dish>().eq("m_id", m_id)
        var dishIPage: IPage<Dish> = dishMapper.selectPage(dishPage,dishwrapper)
        val dishList: List<Dish> =dishIPage.records;
//        orderList.forEach { println(it.toString())}
//        dishList.forEach { println(it.toString()) }
        return dishList
//        Page<Orders>() orderPage = new Page<>(curPage,size);
    }

    /**
     * 模糊查询所有菜
     * @param d_name 传入部分d_name
     */
    @RequestMapping("/findAllByd_name")
    fun selectAllByd_name(
        @RequestParam("d_name") d_name: String,
        @RequestParam("m_id") m_id: String,
    ): List<Dish> {
        var dishwrapper = QueryWrapper<Dish>().eq("m_id",m_id).like("d_name", d_name)
//        println(accountMapper.selectList(accountwrapper).size)
        return dishMapper.selectList(dishwrapper)
    }

    /**
     * 模糊查询那一页菜
     * @param d_name 传入的部分d_name
     * @param
     */
    @RequestMapping("/findPageByd_name")
    fun selectPageByd_name(
        @RequestParam("d_name") d_name: String,
        @RequestParam("current_page") curPage: String,
        @RequestParam("m_id") m_id: String,
        @RequestParam("size_page") pageCount: String
    ): List<Dish> {
        val curPageLong = curPage.toLong()
        val pageCountLong = pageCount.toLong()
        var dishwrapper = QueryWrapper<Dish>().eq("m_id",m_id).like("d_name", d_name)
        var dishPage = Page<Dish>(curPageLong, pageCountLong)
        return dishMapper.selectPage(dishPage,dishwrapper).records
    }
}