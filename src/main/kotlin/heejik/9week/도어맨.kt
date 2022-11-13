package heejik.`9week`

class 도어맨 {


    fun solve() {

        val x = readln().toInt()
        var answer = 0
        val order = readln()
        var wCount = 0
        var mCount = 0
        var isChanged = false

        kotlin.run {
            order.forEachIndexed { index, _c ->
                var c = _c
                if (isChanged) {
                    isChanged = false
                    c = c.change()
                }
                if (c == 'W') {
                    if (wCount - mCount == x) {
                        if (index == order.length - 1 || order[index + 1] == 'W') return@run
                        isChanged = true
                        mCount++
                    } else wCount++
                } else {
                    if (mCount - wCount == x) {
                        if (index == order.length - 1 || order[index + 1] == 'M') return@run
                        isChanged = true
                        wCount++
                    } else mCount++
                }
                answer++
            }
        }
        println(answer)
    }

    private fun Char.change() = if (this == 'W') 'M' else 'W'

}

fun main() {

    도어맨().solve()

}