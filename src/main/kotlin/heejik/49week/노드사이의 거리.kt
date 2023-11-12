package heejik.`49week`

import kotlin.properties.Delegates

class `노드사이의 거리` {


    private var n by Delegates.notNull<Int>()
    private var m by Delegates.notNull<Int>()
    lateinit var nodes: MutableList<MutableList<Pair<Int, Int>>>


    fun solve() {
        setting()
        repeat(m) {
            val (node1, node2) = readln().split(' ').map { it.toInt() }
            bfs(firstStart = node1 - 1, destination = node2 - 1)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        nodes = MutableList(n) { mutableListOf() }
        repeat(n - 1) {
            val (node1, node2, distance) = readln().split(' ').map { it.toInt() }
            nodes[node1 - 1].add(node2 - 1 to distance)
            nodes[node2 - 1].add(node1 - 1 to distance)
        }
    }

    private fun bfs(firstStart: Int, destination: Int) {
        val deque = ArrayDeque<Pair<Int, Int>>()
        val visited = BooleanArray(size = n)
        visited[firstStart] = true
        deque.add(firstStart to 0)

        while (deque.isNotEmpty()) {
            val (start, distance) = deque.removeFirst()

            if (start == destination) {
                println(distance)
                return
            }

            for (node in nodes[start]) {
                if (visited[node.first].not()) {
                    deque.add(node.first to distance + node.second)
                    visited[node.first] = true
                }
            }
        }
    }
}

fun main() {
    `노드사이의 거리`().solve()
}