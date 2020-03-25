package android.content.res

class Resources {

    var TAG = "Resources"

    /**
     * 用于方便演示，实现的一个装饰方法
     */
    fun wrapperWith(str: String): Resources {
        TAG = "$str 加工后的 [$TAG]"
        return this
    }

    class Theme {

    }
}