package heejik.`20week`

import kotlin.properties.Delegates

class `에너지 모으기` {

    var n by Delegates.notNull<Int>()
    var marble = mutableListOf<Int>()
    var answer = 0

    fun solve() {
        setting()
        getEnergy(cnt = 0, sumOfEnergy = 0)
        println(answer)
    }

    private fun setting() {
        n = readln().toInt()
        marble = readln().split(' ').map { it.toInt() }.toMutableList()
    }


    private fun getEnergy(cnt: Int, sumOfEnergy: Int) {

        if (cnt == n - 2) {
            answer = answer.coerceAtLeast(sumOfEnergy)
            return
        }

        for (i in 1 until marble.size - 1) {
            val tmp = marble.removeAt(i)
            val energy = marble[i] * marble[i - 1]
            getEnergy(cnt + 1, sumOfEnergy + energy)
            marble.add(i, tmp)
        }
    }
}

fun main() {
    `에너지 모으기`().solve()
}