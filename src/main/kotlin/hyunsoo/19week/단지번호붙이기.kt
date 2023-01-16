package hyunsoo.`19week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [단지번호붙이기](https://www.acmicpc.net/problem/2667)
 *
 *
 * - 아이디어
 *
 * DFS로 탐색한다. 1로 묶여진 곳들이 하나의 단지. 개수가 단지내 집의 수가 됨.
 *
 * - 트러블 슈팅
 * 우선순위 큐를 poll하지 않고 그냥 forEach로 출력했음...
 */
class `전현수_단지번호붙이기` {

    private val mapSize = readln().toInt()
    private val estateInfo = Array(mapSize) {
        readln().toCharArray()
    }

    // 북 동 남 서
    private val dx = listOf(-1, 0, 1, 0)
    private val dy = listOf(0, 1, 0, -1)


    private val houseCntPQ = PriorityQueue<Int>()

    fun solution() {

        for (i in estateInfo.indices) {
            for (j in estateInfo.indices) {
                val groupedHouseCnt = groupHouses(i, j)
                if (groupedHouseCnt != 0) houseCntPQ.add(groupedHouseCnt)
            }
        }

        println(houseCntPQ.size)
        while (houseCntPQ.isNotEmpty()) {
            println(houseCntPQ.poll())
        }

    }

    private fun groupHouses(x: Int, y: Int): Int {

        var curGroupedHouseCnt = 0

        // 현재 좌표가 0이면 집이 아니므로 탐색 X
        estateInfo[x][y].apply {
            if (this == '0') {
                return 0
            } else {
                estateInfo[x][y] = '0'
                curGroupedHouseCnt++
            }
        }

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || mapSize <= nx || mapSize <= ny) continue

            curGroupedHouseCnt += groupHouses(nx, ny)
        }

        return curGroupedHouseCnt
    }
}

fun main() {
    전현수_단지번호붙이기().solution()
}



