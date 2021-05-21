package cn.blinkdagger.okextendsion.extend


fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    if(index1 in this.indices && index2 in this.indices) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
}





