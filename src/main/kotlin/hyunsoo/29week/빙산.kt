package hyunsoo.`29week`

import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [빙산](https://www.acmicpc.net/problem/2573)
 *
 * - 아이디어
 *
 * 매초
 * - 4방향 탐색 후 빙산 녹이기
 * - 빙산이 나누어져있는지 확인
 *   - bfs로 탐색해서 두 번 탐색되면 나누어진 상태
 *
 * - 트러블 슈팅
 *
 */
class `전현수_빙산` {

    private data class Position(val x: Int, val y: Int)

    private data class MeltInfo(val target: Position, val damage: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private var row = 0
    private var column = 0
    private var time = 0
    private val map = mutableListOf<MutableList<Int>>()
    private val meltInfoList = mutableListOf<MeltInfo>()

    fun solution() {
        readln().split(" ")
            .map {
                it.toInt()
            }.apply {
                row = this[0]
                column = this[1]
            }

        repeat(row) {
            val rowData = readln().split(" ").map { it.toInt() } as MutableList
            map.add(rowData)
        }

        while (isAllMelted().not()) {

            meltInfoList.clear()

            checkMeltInfo()
            melt()
            time++

            val curIceBergCnt = countIceBerg()
            if (1 < curIceBergCnt) {
                println(time)
                exitProcess(0)
            }
        }

        println(0)

    }

    private fun checkMeltInfo() {
        map.forEachIndexed { r, rowData ->
            rowData.forEachIndexed { c, columnData ->
                if (columnData != SEA) {
                    val damage = countSea(r, c)
                    meltInfoList.add(
                        MeltInfo(Position(r, c), damage)
                    )
                }
            }
        }
    }

    private fun melt() {
        meltInfoList.forEach { meltInfo ->
            val pos = meltInfo.target
            map[pos.x][pos.y] -= meltInfo.damage
            if (map[pos.x][pos.y] < 0) map[pos.x][pos.y] = 0
        }
    }

    private fun countSea(r: Int, c: Int): Int {
        var damage = 0
        dirs.forEach { dir ->

            val nx = r + dir.x
            val ny = c + dir.y

            if (nx !in 0 until row || ny !in 0 until column) return@forEach

            if (map[nx][ny] == SEA) damage++

        }
        return damage
    }

    private fun countIceBerg(): Int {

        var cnt = 0
        val visited = Array(row) {
            BooleanArray(column)
        }

        map.forEachIndexed { r, rowData ->
            rowData.forEachIndexed { c, columnData ->
                if (columnData != SEA && visited[r][c].not()) {
                    visitWithBfs(map, visited, Position(r, c))
                    cnt++
                }
            }
        }
        return cnt
    }

    private fun visitWithBfs(map: MutableList<MutableList<Int>>, visited: Array<BooleanArray>, startPos: Position) {
        val queue: Queue<Position> = LinkedList()
        queue.add(startPos)

        while (queue.isNotEmpty()) {

            val pos = queue.poll()

            dirs.forEach { dir ->

                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                if (nx !in 0 until row || ny !in 0 until column ||
                    map[nx][ny] == SEA || visited[nx][ny]
                ) return@forEach

                visited[nx][ny] = true
                queue.add(Position(nx, ny))
            }
        }
    }


    private fun isAllMelted() = map.all { rowData ->
        rowData.all { it == SEA }
    }

    companion object {
        private const val SEA = 0
    }
}

fun main() {
    전현수_빙산().solution()
}