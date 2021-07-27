package edu.jmu.seven.spark

import org.apache.spark.api.java.JavaPairRDD
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import scala.Tuple2
import java.io.Serializable
import java.util.regex.Pattern

class NumberWithCountService(
    var javaSparkContext: JavaSparkContext
) : Serializable {

    companion object {
        val pattern: Pattern = Pattern.compile("\\s+")
    }

    /**
     * 次数序号RDD执行业务类的执行函数
     * @param filename 文件名，hdfs文件请使用hdfs链接形式
     * @return 序号-次数键值对
     */
    fun run(filename: String) : Map<String, Int> {
//        val map = HashMap<String, Int>()
        // 读取文件
        val lines : JavaRDD<String> = javaSparkContext.textFile(filename).cache()

        // 根据第三位字符串（序号字符串），进行map
        val lineWords : JavaPairRDD<String, Int> = lines.mapToPair { Tuple2(pattern.split(it)[2], 1) }

        // 合并
        val numCount = lineWords.reduceByKey { v1, v2 -> v1 + v2 }

        // 返回结果
        return numCount.collectAsMap()
    }
}
