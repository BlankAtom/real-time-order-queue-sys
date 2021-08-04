package edu.jmu.seven.controller

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import edu.jmu.seven.entity.BigDataEntity
import edu.jmu.seven.mapper.BigDataMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/08/02/20:10
 */

@RestController
@RequestMapping("/api/bigdata")
class BigDataController {
    companion object {
        const val bigdataUrl = "http://159.75.90.116:58099/api/spark/bigdata"

        const val MERCHANT_COUNT = 3
        const val SEARCH_COUNT = 1
        const val INDEX_COUNT = 2
    }
    @Autowired
    lateinit var bigDataMapper: BigDataMapper

    @RequestMapping("/merchant-count")
    fun merchantCount() : List<BigDataEntity> {
        return doAction(MERCHANT_COUNT)
    }

    @RequestMapping("/search-count")
    fun searchCount(): List<BigDataEntity>  {
        return doAction(SEARCH_COUNT)
    }
    @RequestMapping("/index-count")
    fun indexCount(): List<BigDataEntity>  {
        return doAction(INDEX_COUNT)
    }
    private fun doAction(i: Int): ArrayList<BigDataEntity> {
        val lastDay = LocalDate.now().plusDays(-1)
        val wrapper = QueryWrapper<BigDataEntity>().eq("date_time", lastDay).eq("status", i)
        val result: ArrayList<BigDataEntity> = bigDataMapper.selectList(wrapper) as ArrayList<BigDataEntity>


        // 如果不为空，证明已经做过一次rdd了，返回结果就行
        if(result.isNotEmpty()) {
            result.sortBy { p -> p.second }
            return result
        }

        // 执行rdd调用
        // 解析返回结果，转化为map
        val map = JSON.parse(getResponse("$bigdataUrl?index=$i")) as Map<*, *>
        var maxIndex = 0;
        for (m in map) {
            val e = BigDataEntity(null, m.key.toString(), m.value.toString(), lastDay, i)
            if( result.indexOf(e) < 0 ) {
                result.add(e)
            }
            bigDataMapper.insert(e)

            maxIndex ++
            if( maxIndex >= 20 ) {
                break
            }
        }

        // 排序后返回
        result.sortBy { p -> p.second }
        result.reverse()
        return result
    }

    private fun getResponse(s: String): String {
        val rest = RestTemplate()
        return rest.getForEntity(s, String::class.java).body ?: "{}"
    }
}