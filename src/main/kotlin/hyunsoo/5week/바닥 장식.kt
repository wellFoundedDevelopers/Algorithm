package hyunsoo.`5week`

import java.util.*

/**
 * <문제>
 * [바닥 장식](https://www.acmicpc.net/problem/1388)
 * -는 행
 * |는 열
 *
 * bfs로 해보자
 */

val floorData = mutableListOf<MutableList<Char>>()
val queue: Queue<Int> = ArrayDeque()

fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }
    var woodPlankCnt = 0

    repeat(n) {
        val floorRow = readln().chunked(1).map { it.first() }.toMutableList()
        floorData.add(floorRow)
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            val curFloor = floorData[i][j]

            if (curFloor == '-') {
                horizontalBfs(i, j)
                woodPlankCnt++
            } else if (curFloor == '|') {
                verticalBfs(i, j)
                woodPlankCnt++
            }
        }
    }

    println(woodPlankCnt)

}

fun verticalBfs(x: Int, y: Int) {

    val curFloor = floorData[x][y]
    if (curFloor == '0') return
    floorData[x][y] = '0'

    queue.add(x)
    while (queue.isNotEmpty()) {
        val nx = queue.poll() + 1
        if (nx >= floorData.size) return

        if (floorData[nx][y] == '|') {

            floorData[nx][y] = '0'
            queue.add(nx)
        }
    }
}

fun horizontalBfs(x: Int, y: Int) {

    val curFloor = floorData[x][y]
    if (curFloor == '0') return
    floorData[x][y] = '0'

    queue.add(y)
    while (queue.isNotEmpty()) {
        val ny = queue.poll() + 1
        if (ny >= floorData.first().size) return

        if (floorData[x][ny] == '-') {
            floorData[x][ny] = '0'
            queue.add(ny)
        }
    }
}