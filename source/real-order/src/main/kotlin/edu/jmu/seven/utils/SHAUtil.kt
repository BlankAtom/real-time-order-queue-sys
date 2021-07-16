package edu.jmu.seven.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SHAUtil {
    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    fun SHA256(strText: String?): String? {
        return sha(strText, "SHA-256")
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    fun SHA512(strText: String?): String? {
        return sha(strText, "SHA-512")
    }

    /**
     * md5加密
     * @param strText
     * @return
     */
    fun SHAMD5(strText: String?): String? {
        return sha(strText, "MD5")
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText 待加密文本
     * @return
     */
    private fun sha(strText: String?, strType: String): String? {
        // 返回值
        var strResult: String? = null

        // 是否是有效字符串
        if (strText != null && strText.isNotEmpty()) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                val messageDigest = MessageDigest.getInstance(strType)
                // 传入要加密的字符串
                messageDigest.update(strText.toByteArray())
                // 得到 byte 類型结果
                val byteBuffer = messageDigest.digest()

                // 將 byte 轉換爲 string
                val strHexString = StringBuffer()
                // 遍歷 byte buffer
                for (i in byteBuffer.indices) {
                    val hex = Integer.toHexString(0xff and byteBuffer[i].toInt())
                    if (hex.length == 1) {
                        strHexString.append('0')
                    }
                    strHexString.append(hex)
                }
                // 得到返回結果
                strResult = strHexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
        }
        return strResult
    }
}
