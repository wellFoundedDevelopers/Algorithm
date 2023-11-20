package hyunsoo.`50week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 *
 * <문제>
 * [북쪽나라의 도로](https://www.acmicpc.net/problem/1595)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_북쪽나라의_도로` {

    private data class Node(val index: Int, val cost: Int)

    private val graph = Array(10001) {
        mutableListOf<Node>()
    }

    var answer = 0

    fun solution() {

        val br = BufferedReader(InputStreamReader(System.`in`))

        while (true) {

            val input = br.readLine()?.trim() ?: break
            if (input.isBlank()) break

            val (fir, sec, cost) = input.split(" ").map { it.toInt() }

            graph[fir].add(Node(sec, cost))
            graph[sec].add(Node(fir, cost))

        }

        val first = bfs(1)
        println(bfs(first.index).cost)

    }

    private fun bfs(start: Int): Node {

        val queue: Queue<Node> = LinkedList()
        queue.add(Node(start, 0))

        val visited = BooleanArray(10001)
        visited[start] = true
        var maxCost = 0
        var targetNode = 0

        while (queue.isNotEmpty()) {

            val (curNode, curCost) = queue.poll()

            if (maxCost < curCost) {
                maxCost = curCost
                targetNode = curNode
            }

            graph[curNode].forEach { node ->

                if (visited[node.index].not()) {
                    visited[node.index] = true
                    queue.add(Node(node.index, curCost + node.cost))
                }
            }

        }
        return Node(targetNode, maxCost)
    }

}

fun main() {
    전현수_북쪽나라의_도로().solution()
}