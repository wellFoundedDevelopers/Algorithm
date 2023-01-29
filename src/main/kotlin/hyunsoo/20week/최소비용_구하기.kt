package hyunsoo.`20week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [최소비용 구하기](https://www.acmicpc.net/problem/1916)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_최소비용_구하기` {

    data class CostData(val node: Int, val cost: Int)

    private val priorityQueue = PriorityQueue(
        Comparator<CostData> { a, b ->
            a.cost - b.cost
        }
    )

    private lateinit var cityData: Array<MutableList<CostData>>
    private lateinit var distance: IntArray

    fun solution() {

        val cityCnt = readln().toInt()
        val busCnt = readln().toInt()

        distance = IntArray(cityCnt + 1) { Int.MAX_VALUE }

        cityData = Array(cityCnt + 1) {
            mutableListOf()
        }

        repeat(busCnt) {
            val (start, end, cost) = readln().split(" ").map { it.toInt() }
            cityData[start].add(CostData(end, cost))
        }

        val (startNode, endNode) = readln().split(" ").map { it.toInt() }

        dijkstra(startNode)

        println(distance[endNode])

    }

    private fun dijkstra(startPoint: Int) {

        distance[startPoint] = 0
        priorityQueue.add(CostData(startPoint, 0))

        while (priorityQueue.isNotEmpty()) {

            val (startNode, costToStart) = priorityQueue.poll()

            if (distance[startNode] < costToStart) continue

            cityData[startNode].forEach { costData ->

                val (endNode, costToEndFromStart) = costData
                val costToEnd = costToStart + costToEndFromStart

                if (costToEnd < distance[endNode]) {
                    distance[endNode] = costToEnd
                    priorityQueue.add(CostData(endNode, costToEnd))
                }
            }

        }

    }
}

fun main() {
    전현수_최소비용_구하기().solution()
}