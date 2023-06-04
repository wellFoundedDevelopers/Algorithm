package hyunsoo.`38week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [카드 정렬하기](https://www.acmicpc.net/problem/1715)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_카드_정렬하기` {

    fun solution() {

        val priorityQueue = PriorityQueue<Int>()
        var answer = 0

        repeat(readln().toInt()) {
            priorityQueue.add(readln().toInt())
        }

        while (priorityQueue.size >= 2) {

            val sum = priorityQueue.pollTwice().sum()
            answer += sum

            priorityQueue.add(sum)
        }

        println(answer)

    }

    private fun <T> PriorityQueue<T>.pollTwice(): List<T> {
        return List(2) { this.poll() }
    }

}

fun main() {
    전현수_카드_정렬하기().solution()
}