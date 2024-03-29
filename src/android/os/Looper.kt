package android.os

class Looper {
    val msgQueue = MessageQueue()

    /**
     * 获取和准备 当前线程的looper
     */
    companion object {

        /**
         * ThreadLocal是线程本地类，特点是只有线程自己持有，其他线程无法访问
         *
         * 保证每个线程有唯一的Looper
         */
        private val sThreadLocal = ThreadLocal<Looper>()

        /**
         * 开始执行循环
         */
        @JvmStatic
        fun loop() {
            val looper = myLooper()
            looper ?: throw RuntimeException("This looper is not prepare")

            // 开始死循环
            while (true) {
                val msg = looper.msgQueue.next() // 阻塞
                msg ?: return
                msg.target?.dispatchMessage(msg)

            }
        }

        /**
         * 为当前线程准备Looper
         *
         * 每个线程只能创建一个Looper，即同一线程中，prepare只能调用一次，否则报错
         */
        @JvmStatic
        fun prepare() {
            if (sThreadLocal.get() != null) {
                throw RuntimeException("only one created looper")
            }
            sThreadLocal.set(Looper())
        }

        /**
         * 获取当前线程的Looper，需要先调用prepare
         */
        @JvmStatic
        fun myLooper(): Looper? {
            return sThreadLocal.get()
        }
    }
}