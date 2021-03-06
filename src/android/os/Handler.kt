package android.os

open class Handler(looper: Looper? = null) {
    // handler的looper，构造时创建
    private val mLooper: Looper? = looper ?: Looper.myLooper()

    private val mMsgQueue: MessageQueue = mLooper!!.msgQueue

    /**
     * @time 此消息被执行的时间
     */
    fun sendMessage(msg: Message) {
        msg.target = this
        // 入队
        mMsgQueue.enqueueMsg(msg, Util.uptimeMillis())
    }

    /**
     * @time 此消息被执行的时间
     */
    fun sendMessageDelay(msg: Message, delay: Long) {
        msg.target = this
        // 入队
        sendMessageAtTime(msg, Util.uptimeMillis() + delay)
    }

    fun sendMessageAtTime(msg: Message, uptimeMillis: Long){
        mMsgQueue.enqueueMsg(msg, uptimeMillis)
    }

    open fun dispatchMessage(msg:Message){
        if (msg.callback != null){
            msg.callback?.run()
        }else{
            handleMessage(msg)
        }
    }

    open fun handleMessage(msg:Message){
    }
}