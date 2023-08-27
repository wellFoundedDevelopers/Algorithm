package hyunsoo.`42week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [바이러스](https://www.acmicpc.net/problem/2606)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_바이러스` {


    fun solution() {

        val cnt = readln().toInt()
        val networkCnt = readln().toInt()

        val info = Array(cnt + 1) {
            mutableListOf(0)
        }

        val visited = BooleanArray(cnt + 1)

        repeat(networkCnt) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            info[a].add(b)
            info[b].add(a)
        }

        val queue: Queue<Int> = LinkedList()

        queue.add(1)
        visited[0] = true
        visited[1] = true

        while (queue.isNotEmpty()) {

            val cur = queue.poll()
            visited[cur] = true

            info[cur].forEach { num ->
                if (visited[num].not()) {
                    queue.add(num)
                }
            }
        }

        println(visited.count { it } - 2)

    }
}

fun main() {
    전현수_바이러스().solution()
}