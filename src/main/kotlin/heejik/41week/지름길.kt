package heejik.`41week`

import kotlin.math.min
import kotlin.properties.Delegates

class 지름길 {

    data class Shortcut(
        val start: Int,
        val end: Int,
        val length: Int
    )

    var n by Delegates.notNull<Int>()
    var d by Delegates.notNull<Int>()
    private val shortcuts = mutableListOf<Shortcut>()

    fun solve() {
        setting()
        getMinLength().also {
            println(it)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            d = this[1]
        }

        repeat(n) {
            val (start, end, length) = readln().split(' ').map { it.toInt() }
            shortcuts.add(Shortcut(start, end, length))
        }
    }

    private fun getMinLength(): Int {
        val dp = MutableList(10001) { it }

        for (pos in 1 until dp.size) {
            dp[pos] = dp[pos - 1] + 1
            if ((pos in shortcuts.map { it.end }).not()) continue
            shortcuts.filter { it.end == pos }.forEach {
                dp[pos] = min(dp[pos], dp[it.start] + it.length)
            }
        }

        return dp[d]
    }
}

fun main() {
    지름길().solve()
}