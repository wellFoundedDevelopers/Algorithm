package heejik.`14week`

import kotlin.math.min

class `수 고르기` {
    fun solve() {
        var minDiff = Long.MAX_VALUE
        var n = 0
        var m = 0L

        readln().split(' ').run {
            n = first().toInt()
            m = last().toLong()
        }

        var (start, end) = Pair(0, 0)
        val nums = mutableListOf<Long>()


        repeat(n) { nums.add(readln().toLong()) }

        nums.sort()

        while (end != n && start != n) {
            val diff = nums[end] - nums[start]
            if (diff >= m) {
                minDiff = min(minDiff, diff)
                start ++
            } else {
                end ++
            }
        }

        println(minDiff)
    }
}

fun main() {
    `수 고르기`().solve()
}