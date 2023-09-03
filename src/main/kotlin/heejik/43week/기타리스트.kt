package heejik.`43week`

import kotlin.math.max
import kotlin.properties.Delegates

class 기타리스트 {

    lateinit var differences: MutableList<Int>
    lateinit var dp: MutableList<MutableList<Boolean>>
    var n by Delegates.notNull<Int>()
    var s by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    var answer = -1

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            s = this[1]
            m = this[2]
        }
        differences = readln().split(' ').map { it.toInt() }.toMutableList()
        dp = MutableList(n + 1) { MutableList(m + 1) { false } }
        dp[0][s] = true

        play().also {
            println(it)
        }
    }

    private fun play(): Int {
        repeat(n) first@{ songIdx ->
            repeat(m + 1) second@{ volume ->
                if (dp[songIdx][volume].not()) return@second
                val plusDiff = volume + differences[songIdx]
                val minusDiff = volume - differences[songIdx]

                if (plusDiff in 0..m) {
                    dp[songIdx + 1][plusDiff] = true
                }
                if (minusDiff in 0..m) {
                    dp[songIdx + 1][minusDiff] = true
                }
            }
        }

        return dp[n].lastIndexOf(true)
    }
}

fun main() {
    기타리스트().solve()
}