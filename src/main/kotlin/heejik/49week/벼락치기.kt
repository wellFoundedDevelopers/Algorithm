package heejik.`49week`

import kotlin.math.max


class 벼락치기 {
    fun solve() {
        val units = mutableListOf<Pair<Int, Int>>()

        val (n, t) = readln().split(' ').map { it.toInt() }

        repeat(n) {
            val (k, s) = readln().split(' ').map { it.toInt() }
            units.add(k to s)
        }

        val dp = MutableList(size = t + 1) { 0 }
        val stored = MutableList(size = t + 1) { 0 }

        first@ for (unit in units) {
            second@ for (time in 0..t) {
                if (time + unit.first > t) break@second
                stored[time + unit.first] = max(dp[time + unit.first], dp[time] + unit.second)
            }
            for (time in 0..t) {
                if (stored[time] != 0) {
                    dp[time] = stored[time]
                }
            }
            stored.fill(0)
        }

        println(dp.last())
    }
}


fun main() {
    벼락치기().solve()
}