package edu.jmu.seven.spark

import edu.jmu.seven.config.SparkConfig
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.util.ResourceUtils
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDateTime

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/27/14:34
 */

class MySpark : Serializable {
    companion object {
        const val sparkFileUrl = "hdfs://159.75.90.116/data/order"

        var jsc: JavaSparkContext = SparkConfig.javaSparkContext()
    }

//    @Autowired

    /**
     * @return Map<String, Int> 获取序号-次数结果
     */
    fun getNumberWithCount() : Map<String, Int> {
        val fileName = sparkFileUrl+ "/" + getFileName()
        return NumberWithCountService(jsc).run(fileName)
    }

    /**
     * @return Map<String, Int> 获取商家-访问次数
     */
    fun getMerchantVisited(): Map<String, Int> {
        val fileName = sparkFileUrl+ "/" + getFileName()
        return MerchantVisitedService(jsc).run(fileName)
    }

    /**
     * @return Map<String, Int> 获取搜索词-搜索次数结果
     */
    fun getSearchWordCount(): Map<String, Int> {
        val fileName = sparkFileUrl+ "/" + getFileName()
        return SearchWordCountService(jsc).run(fileName)
    }

    /**
     * 写入新的数据
     */
    fun writeInFile(uid: String, search: String, index: Int, mid: String ) {
        // 获得class运行路径
        val path: String = ResourceUtils.getURL("classpath:").path
        // 打开存储路径
        val fileUploadDir = File(path, "log")
        if( !fileUploadDir.exists()) {
            fileUploadDir.mkdirs()
        }

        // 查看昨天的文件是否还在，还在的话就上传并删除掉
        var fn = getFileName()
        var file = File(fileUploadDir, fn)

        if( file.exists() ) {
            uploadFile(file)
            file.delete()
        }

        // 写入今天的文件
        fn = getTodayFilename()
        file = File(fileUploadDir, fn)
        val bw = BufferedWriter(FileWriter(file))
        val arr = "$uid $search $index $mid"
        bw.write(arr+"\n")

        // 关闭
        bw.close()
    }

    /**
     * 将文件上传URL
     */
    private fun uploadFile(file: File) {
        // 初始化配置器
        val conf = Configuration()
        conf.set("fs.defaultFS", sparkFileUrl)

        val fs = FileSystem.get(conf)
        fs.copyFromLocalFile(true, Path(file.name), Path(file.name))

        fs.close()
    }

    /**
     * 获取昨天的日志文件名
     */
    private fun getFileName() : String {
        val ld = LocalDateTime.now().plusDays(-1)
        val ldStr: String = SimpleDateFormat("yyyy_MM_dd").format(ld)
        return ldStr + "_01.log"
    }

    /**
     * 获取今日的日志文件名
     */
    fun getTodayFilename(): String {
        return SimpleDateFormat("yyyy_MM_dd").format(LocalDateTime.now()) + "_01.log"
    }
}