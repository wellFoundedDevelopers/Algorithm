package hyunsoo.`38week`

import java.util.LinkedList
import java.util.Queue
import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [인구 이동](https://www.acmicpc.net/problem/16234)
 *
 * - 아이디어
 *
 * 인구 이동은 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속
 * - 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선은 오늘 하루 동안 연다.
 *   - 국경선 오픈 판정을 완료한 후 인구 이동 시작
 * - 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 해당 나라를 하루 동안 연합이라고 함.
 * - 연합을 이루고 있는 각 칸의 인구수 = (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 됨.
 * - 연합을 해체하고, 모든 국경선을 닫음.
 *
 *
 *
 * - 트러블 슈팅
 *
 */
class `전현수_인구_이동` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, 1),
        Position(0, -1),
    )

    private lateinit var peopleData: List<MutableList<Int>>
    private lateinit var visited: Array<BooleanArray>
    private var rowCnt = 0
    private var minDiff = 0
    private var maxDiff = 0

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            rowCnt = first()
            minDiff = this[1]
            maxDiff = last()
        }

        peopleData = List(rowCnt) {
            readln().split(" ").map { it.toInt() } as MutableList
        }

        var day = 0
        while (peopleData.canMove()) {

            day++
            visited = Array(rowCnt) {
                BooleanArray(rowCnt)
            }

            for (i in 0 until rowCnt) {
                for (j in 0 until rowCnt) {
                    if (visited[i][j].not()) {
                        bfs(i, j)
                    }
                }
            }
        }

        println(day)

    }

    private fun bfs(startX: Int, startY: Int) {

        val union = mutableListOf<Position>()
        val queue: Queue<Position> = LinkedList()

        queue.add(Position(startX, startY))
        union.add(Position(startX, startY))
        visited[startX][startY] = true

        while (queue.isNotEmpty()) {
            val (curX, curY) = queue.poll()

            dirs.forEach { dir ->

                val nextX = curX + dir.x
                val nextY = curY + dir.y

                if (
                    nextX !in 0 until rowCnt ||
                    nextY !in 0 until rowCnt ||
                    visited[nextX][nextY]
                ) return@forEach

                if (
                    (peopleData[curX][curY] - peopleData[nextX][nextY]).absoluteValue
                    in minDiff..maxDiff
                ) {
                    queue.add(Position(nextX, nextY))
                    union.add(Position(nextX, nextY))
                    visited[nextX][nextY] = true
                }
            }
        }

        val target = union.sumOf {
            peopleData[it.x][it.y]
        } / union.size

        union.forEach { pos ->
            peopleData[pos.x][pos.y] = target
        }
    }

    private fun List<MutableList<Int>>.canMove(): Boolean {

        for (curX in 0 until rowCnt) {
            for (curY in 0 until rowCnt) {

                dirs.forEach { dir ->

                    val nextX = curX + dir.x
                    val nextY = curY + dir.y

                    if (nextX !in 0 until rowCnt ||
                        nextY !in 0 until rowCnt
                    ) return@forEach

                    if (
                        (peopleData[curX][curY] - peopleData[nextX][nextY]).absoluteValue
                        in minDiff..maxDiff
                    ) return true
                }
            }
        }
        return false
    }


}

fun main() {
    전현수_인구_이동().solution()
}