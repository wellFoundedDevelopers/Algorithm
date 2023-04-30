package heejik.`31week`

import kotlin.math.max
import kotlin.properties.Delegates

class 전깃줄 {

    var n by Delegates.notNull<Int>()
    private val wire = sortedMapOf<Int, Int>()

    fun solve() {
        setting()
        getAnswer().also {
            println(n - it)
        }
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            val (key, value) = readln().split(' ').map { it.toInt() }
            wire[key] = value
        }
    }

    private fun getAnswer(): Int {
        val dp = MutableList(501) { 1 }
        wire.keys.forEach { main ->
            wire.keys.filter { it < main }.forEach { sub ->
                if (wire[main]!! > wire[sub]!!) {
                    dp[main] = max(dp[main], dp[sub] + 1)
                }
            }
        }

        return dp.max()
    }
}

fun main() {
    전깃줄().solve()
}