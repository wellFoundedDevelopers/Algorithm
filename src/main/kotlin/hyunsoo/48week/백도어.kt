package hyunsoo.`48week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [백도어](https://www.acmicpc.net/problem/17396)
 *
 * - 아이디어
 *
 *
 * - 트러블 슈팅
 * 인접 행렬이 아니라, 인접 리스트로 해야 함.
 *
 */
class `전현수_백도어` {

    data class Bundle(val index: Int, val cost: Long)

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }

        val graph = Array(n) {
            mutableListOf<Bundle>()
        }

        val costTable = LongArray(n) { Long.MAX_VALUE }

        val sight = readln().split(" ").map { it.toInt() }
                as MutableList

        repeat(m) {

            val (start, end, cost) = readln().split(" ").map { it.toInt() }

            graph[start].add(Bundle(end, cost.toLong()))
            graph[end].add(Bundle(start, cost.toLong()))

        }

        val pq = PriorityQueue<Bundle> { a, b ->
            (a.cost - b.cost).toInt()
        }

        pq.add(Bundle(0, 0))
        costTable[0] = 0

        while (pq.isNotEmpty()) {

            val (startIndex, curCost) = pq.poll()

            if (costTable[startIndex] < curCost) continue
            costTable[startIndex] = curCost.toLong()

            graph[startIndex].forEach { bundle ->

                val (endIndex, cost) = bundle

                // 감지되는 곳이면서 넥서스의 위치가 아닐 때
                if (sight[endIndex] == DETECTED && endIndex != n - 1) return@forEach

                if (curCost + cost < costTable[endIndex]) {
                    costTable[endIndex] = (curCost + cost)
                    pq.add(Bundle(endIndex, costTable[endIndex]))
                }

            }
        }

        println(if (costTable[n - 1] == Long.MAX_VALUE) CANT_GO else costTable[n - 1])
    }

    companion object {
        private const val DETECTED = 1
        private const val NOT_DETECTED = 0
        private const val CANT_GO = -1
    }
}

fun main() {
    전현수_백도어().solution()
}