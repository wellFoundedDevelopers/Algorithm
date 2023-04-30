package heejik.`31week`

import kotlin.properties.Delegates

class 사과나무 {

    var n by Delegates.notNull<Int>()
    private lateinit var treeHeight: MutableList<Int>

    fun solve() {
        setting()
        canHeights().also {
            println(if (it) "YES" else "NO")
        }
    }

    private fun setting() {
        n = readln().toInt()
        treeHeight = readln().split(' ').map { it.toInt() }.sorted().toMutableList()
    }

    private fun canHeights(): Boolean {
        val heightSum = treeHeight.sum()
        if (heightSum % 3 != 0) return false

        val minTwoCnt = heightSum / 3
        var twoCnt = 0

        treeHeight.forEach { height ->
            twoCnt += height / 2
        }

        return twoCnt >= minTwoCnt
    }
}

fun main() {
    사과나무().solve()
}