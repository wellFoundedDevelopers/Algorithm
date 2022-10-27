package hyunsoo.`7week`

import java.util.*


/**
 * <문제>
 * [최소 힙](https://www.acmicpc.net/problem/1927)
 *
 * Priority Queue를 사용하는 간단한 문제?
 *
 * 입/출력
 * - 첫째 줄에 연산의 개수가 주어짐.
 * - N개의 줄에 연산에 대한 정보를 나타내는 정수 x가 주어짐
 *   - x가 자연수면 배열에 x를 추가, x가 0이면 가장 작은 값을 출력
 */
fun main() {

    val priorityQueue = PriorityQueue<Int>()

    val cnt = readln().toInt()

    repeat(cnt) {
        val command = readln().toInt()

        when (command) {
            0 -> {
                priorityQueue.poll()?.let {
                    println(it)
                } ?: println(0)
            }

            else -> {
                priorityQueue.add(command)
            }
        }
    }
}