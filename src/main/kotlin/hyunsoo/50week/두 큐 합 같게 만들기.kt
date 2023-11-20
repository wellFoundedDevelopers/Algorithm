package hyunsoo.`50week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [두 큐 합 같게 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/118667)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_두_큐_합_같게_만들기` {

    fun solution(queue1: IntArray, queue2: IntArray): Long {

        val firQueue: Queue<Long> = LinkedList()
        val secQueue: Queue<Long> = LinkedList()

        queue1.forEachIndexed { index, value ->
            firQueue.add(value.toLong())
            secQueue.add(queue2[index].toLong())
        }

        var firSum = queue1.sumOf { it }.toLong()
        var secSum = queue2.sumOf { it }.toLong()

        var answer: Long = 0
        var cnt = 0
        var canMake = false

        while (cnt <= queue1.size * 4) {

            if (firSum > secSum) {

                val firPopped = firQueue.poll()
                firSum -= firPopped
                secSum += firPopped
                secQueue.add(firPopped)
                answer++
                cnt++
            } else if (firSum < secSum) {
                val secPopped = secQueue.poll()
                secSum -= secPopped
                firSum += secPopped
                firQueue.add(secPopped)
                answer++
                cnt++
            } else {
                canMake = true
                break
            }
        }

        return if (canMake) answer else -1

    }
}

fun main() {
    전현수_두_큐_합_같게_만들기().solution(
        intArrayOf(3, 2, 7, 2),
        intArrayOf(4, 6, 5, 1)
    ).apply {
        println(this)
    }
}