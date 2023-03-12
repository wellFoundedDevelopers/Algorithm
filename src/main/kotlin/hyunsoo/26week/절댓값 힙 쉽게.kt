package hyunsoo.`26week`

import java.util.PriorityQueue
import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [절댓값 힙](https://www.acmicpc.net/problem/11286)
 *
 * - 아이디어
 * 배열을 활용해서 절댓값 힙을 구현해보자!
 * - 트러블 슈팅
 * 하... 중간에 변수명은 absoluteValue로 해놓고 실제로는 absoluteValue 처리를 안해놔서 3시간 고생...
 */
class `전현수_절댓값_힙_쉽게` {

    fun solution() {

        val commandCnt = readln().toInt()
        val heap = PriorityQueue<Int> { a, b ->

            val absoluteValueA = a.absoluteValue
            val absoluteValueB = b.absoluteValue

            if (absoluteValueA - absoluteValueB != 0) absoluteValueA - absoluteValueB
            else a - b
        }

        repeat(commandCnt) {

            when (val command = readln().toInt()) {
                COMMAND_POP -> {
                    if (heap.size == 0) println(0)
                    else println(heap.poll())
                }

                else -> {
                    heap.add(command)
                }
            }
        }

    }

    companion object {
        private const val COMMAND_POP = 0
    }
}

fun main() {

    전현수_절댓값_힙_쉽게().solution()
}