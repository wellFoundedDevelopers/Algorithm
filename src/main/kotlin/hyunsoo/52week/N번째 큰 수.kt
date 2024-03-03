package hyunsoo.`52week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [N번째 큰 수](https://www.acmicpc.net/problem/2075)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_N번째_큰_수` {

    fun solution() {

        val priorityQueue: PriorityQueue<Int> = PriorityQueue { a, b ->
            b - a
        }

        val cnt = readln().toInt()

        repeat(cnt) {
            readln().split(" ").forEach {
                priorityQueue.add(it.toInt())
            }
        }

        repeat(cnt) { curSeq ->
            priorityQueue.poll().apply {
                if (curSeq == cnt - 1) {
                    println(this)
                }
            }
        }
    }
}

fun main() {
    전현수_N번째_큰_수().solution()
}