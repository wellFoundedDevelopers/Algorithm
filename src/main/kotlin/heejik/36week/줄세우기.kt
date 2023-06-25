package heejik.`36week`

import kotlin.math.max
import kotlin.properties.Delegates

class 줄세우기 {

    var n by Delegates.notNull<Int>()
    val numbers = mutableListOf<Int>()

    fun solve() {
        setting()
        getLisCount().also {
            println(n - it)
        }
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            numbers.add(readln().toInt())
        }
    }

    private fun getLisCount(): Int {
        val dp = MutableList(n) { 1 }

        repeat(n) { target ->
            repeat(target) { prior ->
                if (numbers[target] >= numbers[prior]) {
                    dp[target] = max(dp[target], dp[prior] + 1)
                }
            }
        }

        return dp.max()
    }
}

fun main() {
    줄세우기().solve()
}