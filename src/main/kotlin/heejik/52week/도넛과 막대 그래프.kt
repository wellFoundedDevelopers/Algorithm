package heejik.`52week`

import java.util.*

class `도넛과 막대 그래프` {
    data class Edge(
        val shot: MutableList<Int> = mutableListOf(),
        val receive: MutableList<Int> = mutableListOf()
    )

    var answer = IntArray(4)
    val graphs = List(size = 1000001) { Edge() }
    val visited = BooleanArray(1000001)

    fun solution(edges: Array<IntArray>): IntArray {
        var fakeEdge = -1

        edges.forEach { edge ->
            val (a, b) = edge
            graphs[a].shot.add(b)
            graphs[b].receive.add(a)
        }

        // 생성된 연결 정점 찾기
        for ((idx, graph) in graphs.withIndex()) {
            if (graph.shot.size >= 2 && graph.receive.size == 0) {
                fakeEdge = idx
                break
            }
        }
        answer[0] = fakeEdge

        graphs[fakeEdge].shot.forEach {
            bfs(it).also { answer[it]++ }
        }

        return answer
    }

    private fun bfs(received: Int): Int {
        var nodeCount = 1
        var arrowCount = 0
        val queue = ArrayDeque<Int>()
        queue.add(received)
        visited[received] = true

        while (queue.isNotEmpty()) {
            val edge = queue.removeFirst()
            graphs[edge].shot.forEach {
                arrowCount++
                if (visited[it]) {
                    return@forEach
                }
                visited[it] = true
                queue.add(it)
                nodeCount++
            }
        }
        return if (nodeCount == arrowCount) {
            1
        } else if (nodeCount > arrowCount) {
            2
        } else {
            3
        }
    }
}