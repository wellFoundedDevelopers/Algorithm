package hyunsoo.`19week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [숨바꼭질](https://www.acmicpc.net/problem/1697)
 *
 * 수빈이의 위치가 x일 때 걷는다면 1초 후 x-1 혹은 x+1로 이동함.
 * 순간이동하면 2*x의 위치로 이동함.
 * - 아이디어
 *  bfs로 탐색하기?
 *
 * - 트러블 슈팅
 * 0 도 유요한 좌표인데 탐색 범위에서 제외시켰음...
 */
class `전현수_숨바꼭질` {

    data class LocationAndTime(val location: Int, val time: Int)

    private val queue: Queue<LocationAndTime> = LinkedList()
    private val locationData = IntArray(100_001)

    fun solution() {

        val (myLocation, targetLocation) = readln().split(" ").map { it.toInt() }
        locationData[myLocation] = 0
        queue.add(LocationAndTime(myLocation, 0))

        while (queue.isNotEmpty()) {

            val (curLocation, curSec) = queue.poll()

            if (curLocation == targetLocation) break

            val nextPositionList = makeWay(curLocation)

            nextPositionList.forEach { nextPosition ->
                if (nextPosition in 0..100_000) {
                    // 방문하지 않은 곳이라면
                    if (locationData[nextPosition] == 0) {
                        locationData[nextPosition] = curSec + 1
                        queue.add(
                            LocationAndTime(nextPosition, curSec + 1)
                        )
                    }
                }
            }

        }

        println(locationData[targetLocation])
    }

    private fun makeWay(curPosition: Int): List<Int> {
        return mutableListOf<Int>().apply {
            this.add(curPosition - 1)
            this.add(curPosition + 1)
            this.add(curPosition * 2)
        }
    }
}

fun main() {
    전현수_숨바꼭질().solution()
}