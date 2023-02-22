package jimin.`24week`

import kotlin.math.*

class `행렬 테두리 회전하기` {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val answer = mutableListOf<Int>()
        val numbers = MutableList(rows){ r -> MutableList(columns) { c -> r * columns + (c + 1) } }

        queries.forEach{ q ->
            var mini = Int.MAX_VALUE
            var num = numbers[q[0] - 1][q[1] - 1]
            var tmp = 0
            for (j in q[1] - 1 until q[3] - 1) {
                tmp = numbers[q[0] - 1][j + 1]
                numbers[q[0] - 1][j + 1] = num
                num = tmp
                mini = min(mini, num)
            }
            for (i in q[0] - 1 until q[2] - 1) {
                tmp = numbers[i + 1][q[3] - 1]
                numbers[i + 1][q[3] - 1] = num
                num = tmp
                mini = min(mini, num)
            }
            for (j in q[3] - 1 downTo q[1]) {
                tmp = numbers[q[2] - 1][j - 1]
                numbers[q[2] - 1][j - 1] = num
                num = tmp
                mini = min(mini, num)
            }
            for (i in q[2] - 1 downTo q[0]) {
                tmp = numbers[i - 1][q[1] - 1]
                numbers[i - 1][q[1] - 1] = num
                num = tmp
                mini = min(mini, num)
            }
            answer.add(mini)
        }

        return answer.toIntArray()
    }
}