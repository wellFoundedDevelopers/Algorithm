package heejik.`43week`

import kotlin.math.max

class `회전 초밥` {

    fun solve() = with(System.`in`.bufferedReader()) {
        var answer = 0

        val belt = mutableListOf<Int>()

        val (n, d, k, c) = readLine().split(' ').map { it.toInt() }

        repeat(n) {
            val sushi = readLine().toInt()
            belt.add(sushi)
        }
        // 맨 뒤에 k - 1 만큼 앞에 놈들 추가
        repeat(k - 1) {
            belt.add(belt[it])
        }

        repeat(n) { i ->
            val partBeltSet = belt.subList(i, i + k).toSet()
            answer = if (partBeltSet.contains(c)) {
                max(answer, partBeltSet.size)
            } else {
                max(answer, partBeltSet.size + 1)
            }
        }

        println(answer)
    }
}


fun main() {
    `회전 초밥`().solve()
}