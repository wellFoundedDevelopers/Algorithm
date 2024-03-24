package heejik.`54week`

import kotlin.coroutines.suspendCoroutine
import kotlin.math.max

class `꿀 따기` {

    fun solve() {
        val n = readln().toInt()
        val honeys = readln().split(' ').map { it.toInt() }
        val prefixSumOfHoneys = honeys.toMutableList()
        var answer = 0

        for (i in 1 until n) {
            prefixSumOfHoneys[i] += prefixSumOfHoneys[i - 1]
        }

        // 벌통이 맨 오른쪽 -> 맨 왼쪽 픽스
        for (i in 1 until n - 1) {
            val fixSum = prefixSumOfHoneys.last() - honeys[i] - honeys.first()
            val secondSum = prefixSumOfHoneys.last() - prefixSumOfHoneys[i]

            answer = max(answer, fixSum + secondSum)
        }

        // 벌통이 맨 왼쪽 -> 맨 오른쪽 하나 픽스
        for (i in 1 until n - 1) {
            val fixSum = prefixSumOfHoneys.last() - honeys[i] - honeys.last()
            val secondSum = prefixSumOfHoneys[i] - honeys[i]

            answer = max(answer, fixSum + secondSum)
        }

        // 벌통이 중간
        val middleMax = honeys.drop(1).dropLast(1).max()
        println(max(answer, prefixSumOfHoneys.last() + middleMax - honeys.first() - honeys.last()))
    }
}



fun main() {
    `꿀 따기`().solve()
}