package heejik.`31week`

import kotlin.math.max
import kotlin.properties.Delegates

class `포도주 시식` {

    var n by Delegates.notNull<Int>()
    private val wines = mutableListOf<Int>()
    private lateinit var dp: MutableList<Int>

    fun solve() {
        setting()
        dp[1] = wines[1]
        if (n > 1) dp[2] = wines[2] + wines[1]

        for (i in 3 until n + 1) {
            dp[i] = max(max(dp[i-1],wines[i - 1] + wines[i] + dp[i - 3]), wines[i] + dp[i-2])
        }

        println(dp[n])
    }

    private fun setting() {
        n = readln().toInt()
        wines.add(0)
        repeat(n) {
            wines.add(readln().toInt())
        }
        dp = MutableList(n + 1) { 0 }
    }
}


fun main() {
    `포도주 시식`().solve()
}