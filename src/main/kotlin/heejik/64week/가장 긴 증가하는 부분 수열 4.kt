package heejik.`64week`

import kotlin.math.max


private class `가장 긴 증가하는 부분 수열 4` {

    var n = -1
    lateinit var sequence: List<Int>
    lateinit var dp: MutableList<Int>
    val answer = mutableListOf<Int>()

    fun solve() {
        n = readln().toInt()
        sequence = readln().split(' ').map { it.toInt() }
        dp = MutableList(size = n) { 1 }
        findAnswer().also { answerLength ->
            println(answerLength)
        }
        answer.forEach {
            print("$it ")
        }
    }


    fun findAnswer(): Int {
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                if (sequence[i] >= sequence[j]) continue
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }

        val length = dp.max()

        // 4, 3, 2, 1 찾기
        var find = length
        for (i in 0 until n) {
            if (dp[i] == find) {
                answer.add(sequence[i])
                find -= 1
            }
        }

        return length
    }
}


fun main() {
    `가장 긴 증가하는 부분 수열 4`().solve()
}