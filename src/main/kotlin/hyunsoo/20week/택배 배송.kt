package hyunsoo.`20week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [택배 배송](https://www.acmicpc.net/problem/5972)
 *
 * - 아이디어
 * 1 ~ n 까지의 그래프를 최소비용으로 가고 싶다!!
 * - 트러블 슈팅
 *
 */
// log(V^2) 의 시간 복잡도를 가지는 비효율적인 다익스트라 알고리즘. 시간초과 발생.
class `전현수_택배_배송_시간_초과` {

    private val START_NODE = 1

    data class CostData(val endNode: Int, val cost: Int)

    private lateinit var distance: IntArray
    private lateinit var visited: BooleanArray
    private lateinit var roadGraph: Array<MutableList<CostData>>

    fun timeOutSolution() {

        val (barnCnt, roadCnt) = readln().split(" ").map { it.toInt() }

        distance = IntArray(barnCnt + 1) { Int.MAX_VALUE }
        visited = BooleanArray(barnCnt + 1) { false }

        roadGraph = Array(barnCnt + 1) {
            mutableListOf()
        }

        repeat(roadCnt) {
            val (start, end, cost) = readln().split(" ").map { it.toInt() }
            roadGraph[start].add(CostData(end, cost))
            roadGraph[end].add(CostData(start, cost))
        }

        visited[0] = true
        simpleDijkstra(START_NODE)

        println(distance[barnCnt])

    }

    private fun simpleDijkstra(start: Int) {

        distance[start] = 0
        renewMinCost(start)

        while (visited.any { it.not() }) {
            val startNode = findMinCostNode()
            renewMinCost(startNode)
        }
    }

    private fun renewMinCost(startNode: Int) {

        visited[startNode] = true
        roadGraph[startNode].forEach { costData ->

            val (endNode, cost) = costData

            if (distance[startNode] + cost < distance[endNode]) {
                distance[endNode] = distance[startNode] + cost
            }
        }
    }

    private fun findMinCostNode(): Int {
        var min = Int.MAX_VALUE
        var minIndex = 0
        for (index in distance.indices) {
            if (distance[index] < min && visited[index].not()) {
                min = distance[index]
                minIndex = index
            }
        }
        return minIndex
    }


}

class `전현수_택배_배송` {

    private val START_NODE = 1

    data class CostData(val endNode: Int, val cost: Int)

    private val priorityQueue = PriorityQueue(
        Comparator<CostData> { a, b ->
            a.cost - b.cost
        }
    )
    private lateinit var distance: IntArray
    private lateinit var roadGraph: Array<MutableList<CostData>>

    fun solution() {

        val (barnCnt, roadCnt) = readln().split(" ").map { it.toInt() }

        distance = IntArray(barnCnt + 1) { Int.MAX_VALUE }
        roadGraph = Array(barnCnt + 1) {
            mutableListOf()
        }

        repeat(roadCnt) {
            val (start, end, cost) = readln().split(" ").map { it.toInt() }
            roadGraph[start].add(CostData(end, cost))
            roadGraph[end].add(CostData(start, cost))
        }

        improvedDijkstra(START_NODE)

        println(distance[barnCnt])

    }

    private fun improvedDijkstra(start: Int) {

        priorityQueue.add(CostData(start, 0))
        distance[start] = 0

        while (priorityQueue.isNotEmpty()) {

            val (startNode, costToStart) = priorityQueue.poll()

            if (distance[startNode] < costToStart) continue

            roadGraph[startNode].forEach { costData ->

                val (endNode, costFromStartToEnd) = costData
                val leastCostToEnd = costToStart + costFromStartToEnd

                if (leastCostToEnd < distance[endNode]) {
                    distance[endNode] = leastCostToEnd
                    priorityQueue.add(CostData(endNode, leastCostToEnd))
                }
            }
        }
    }
}

fun main() {
    전현수_택배_배송().solution()
}