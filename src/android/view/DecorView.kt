package android.view

class DecorView : ViewGroup() {

    init {
        println("DecorView init")
    }

    override fun onLayout() {
        childViewList.forEach {
            it.layout()
        }
    }
}