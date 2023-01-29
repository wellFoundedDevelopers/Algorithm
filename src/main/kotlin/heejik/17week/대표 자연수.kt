package heejik.`17week`

import kotlin.math.absoluteValue

class `대표 자연수` {

    var minScore = Int.MAX_VALUE
    var center = -1
    lateinit var nums: List<Int>
    fun solve() {
        val n = readln().toInt()
        nums = readln().split(' ').map { it.toInt() }.sorted()

        for (i in nums.first()..nums.last()) {
            getScore(i)
        }

        println(center)
    }

    private fun getScore(num: Int) {
        var tmp = 0
        nums.forEach {
            tmp += (num - it).absoluteValue
        }

        if (tmp < minScore) {
            minScore = tmp
            center = num
        }
    }
}

fun main() {
    `대표 자연수`().solve()
}