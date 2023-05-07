package heejik.`34week`

import java.util.*
import kotlin.properties.Delegates

class `행복 유치원` {
    val br = System.`in`.bufferedReader()

    var n by Delegates.notNull<Int>()
    var k by Delegates.notNull<Int>()
    private lateinit var heights: List<Int>
    private val diffs = mutableListOf<Int>()


    fun solve() {
        setting()
        getDiffs()
        getCost().also { cost ->
            println(cost)
        }
    }

    private fun setting() {
        br.readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            k = this[1]
        }
        heights = br.readLine().split(' ').map { it.toInt() }

    }

    private fun getDiffs() {
        repeat(n - 1) { idx ->
            diffs.add(heights[idx + 1] - heights[idx])
        }
        diffs.sort()
    }

    private fun getCost(): Long {
        var cost = 0L
        repeat(n - k) { idx ->
            cost += diffs[idx]
        }

        return cost
    }
}


fun main() {
    `행복 유치원`().solve()
}