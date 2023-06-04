package heejik.`35week`

import kotlin.math.min
import kotlin.properties.Delegates

class `RGB거리 2` {

    private var n by Delegates.notNull<Int>()
    private val costs = mutableListOf<MutableList<Int>>()
    private var answer = Int.MAX_VALUE

    fun solve() {
        setting()
        repeat(3) {
            answer = min(answer, getCost(firstColor = it))
        }

        println(answer)
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            val (r, g, b) = readln().split(' ').map { it.toInt() }
            costs.add(mutableListOf(r, g, b))
        }
    }

    private fun getCost(firstColor: Int): Int {
        val homeColors = MutableList(n) { MutableList(3) { 0 } }
        homeColors[0][firstColor] = costs[0][firstColor]

        repeat(3) { idx ->
            if (idx == firstColor) {
                homeColors[1][idx] = Int.MAX_VALUE
            } else {
                homeColors[1][idx] = homeColors[0][firstColor] + costs[1][idx]
            }
        }



        for (i in 2 until n) {
            homeColors[i][0] = min(homeColors[i - 1][1], homeColors[i - 1][2]) + costs[i][0]
            homeColors[i][1] = min(homeColors[i - 1][0], homeColors[i - 1][2]) + costs[i][1]
            homeColors[i][2] = min(homeColors[i - 1][0], homeColors[i - 1][1]) + costs[i][2]
        }

        homeColors.last().removeAt(firstColor)
        return homeColors.last().min()
    }
}

fun main() {
    `RGB거리 2`().solve()
}