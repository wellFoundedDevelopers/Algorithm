package hyunsoo.`55week`

import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [여행 가자](https://www.acmicpc.net/problem/1976)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 * https://www.acmicpc.net/board/view/101583
 */
class `전현수_여행_가자` {

    fun solution() {

        val cityCnt = readln().toInt()
        val targetCityCnt = readln().toInt()

        val connectedInfo = Array(201) {
            BooleanArray(201)
        }

        repeat(cityCnt) { rowIndex ->
            readln().split(" ")
                .forEachIndexed { columnIndex, info ->
                    if (info == "1") {
                        connectedInfo[rowIndex][columnIndex] = true
                    }
                    if (rowIndex == columnIndex) connectedInfo[rowIndex][columnIndex] = true
                }
        }

        val plan = readln().split(" ").map { it.toInt() - 1 }

        for (i in 0 until plan.size - 1) {
            val cur = plan[i]
            val next = plan[i + 1]

            if (connectedInfo[cur][next]) {
                continue
            } else {

                val queue: Queue<Int> = LinkedList()
                val visited = Array(201) {
                    BooleanArray(201)
                }

                connectedInfo[cur].forEachIndexed { index, canReach ->
                    if (canReach) {
                        queue.add(index)
                        visited[cur][index] = true
                    }
                }

                var canReach = false
                while (queue.isNotEmpty()) {

                    val searchingIndex = queue.poll()

                    if (connectedInfo[searchingIndex][next]) {
                        canReach = true
                        break
                    } else {
                        connectedInfo[searchingIndex].forEachIndexed { index, canReach ->
                            if (visited[searchingIndex][index].not() &&
                                canReach
                            ) {
                                queue.add(index)
                                visited[searchingIndex][index] = true
                            }
                        }
                    }
                }

                if (canReach.not()) {
                    println("NO")
                    exitProcess(0)
                }

            }
        }

        println("YES")
    }

}

fun main() {
    전현수_여행_가자().solution()
}