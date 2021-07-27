package edu.jmu.seven.socket

import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.CopyOnWriteArraySet
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/27/16:43
 */


@ServerEndpoint("/websocket/{userCode}") //@ServerEndpoint(value = "/websocket")
@Component
class WebSocket {
    //user
    private var currentUser: String? = null

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private var session: Session? = null

    /**
     * 连接建立成功调用的方法 */
    @OnOpen
    fun onOpen(@PathParam("userCode") userCode: String, session: Session?) {
        currentUser = userCode
        this.session = session
        webSocketSet.add(this) //加入set中
        addOnlineCount() //在线数加1
        println("有新连接加入！当前在线人数为" + onlineCount + ",userCode:" + userCode)
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    fun onClose() {
        webSocketSet.remove(this) //从set中删除
        subOnlineCount() //在线数减1
        println("有一连接关闭！当前在线人数为" + onlineCount)
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    fun onMessage(message: String, session: Session?) {
        println("来自客户端的消息:$message")
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    fun onError(session: Session?, error: Throwable?) {
        println("发生错误")
    }

    @Throws(IOException::class)
    fun sendMessage(message: String?) {
        session!!.basicRemote.sendText(message)
    }

    companion object {
        //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
        @get:Synchronized
        var onlineCount = 0
            private set

        //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
        private val webSocketSet = CopyOnWriteArraySet<WebSocket>()

        /**
         * 发送给指定用户
         * @param message
         * @param userCode
         * @throws IOException
         */
        @Throws(IOException::class)
        fun sendMessageTo(message: String?, userCode: String) {
            for (item in webSocketSet) {
                if (item.currentUser == userCode) {
                    item.session!!.basicRemote.sendText(message)
                }
            }
        }

        /**
         * 群发自定义消息
         */
        @Throws(IOException::class)
        fun sendInfo(message: String?) {
            println(message)
            for (item in webSocketSet) {
                try {
                    item.sendMessage(message)
                } catch (e: IOException) {
                    continue
                }
            }
        }

        @Synchronized
        fun addOnlineCount() {
            onlineCount++
        }

        @Synchronized
        fun subOnlineCount() {
            onlineCount--
        }
    }
}