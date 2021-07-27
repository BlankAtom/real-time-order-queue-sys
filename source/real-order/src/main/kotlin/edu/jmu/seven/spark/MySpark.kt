package edu.jmu.seven.spark

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/27/14:34
 */
class MySpark {

    fun getNumberWithCount() : Map<String, Int> {
        return NumberWithCountService().run()
    }
}