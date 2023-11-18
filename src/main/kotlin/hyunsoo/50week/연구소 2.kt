package hyunsoo.`50week`

import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

/**
 *
 * <문제>
 * [연구소 2](https://www.acmicpc.net/problem/17141)
 *
 * - 아이디어
 *
 * 바이러스들의 위치를 저장하고 순열로 뽑아서 관리
 *
 * - 트러블 슈팅
 *
 */
class `전현수_연구소_2` {

    var answer = Int.MAX_VALUE

    private data class VirusInfo(val pos: Position, val cost: Int)
    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1)
    )

    private val board = mutableListOf<MutableList<Int>>()
    private val virusPositions = mutableListOf<Position>()
    private val selectedVirusIndexList = mutableListOf<Int>()

    fun solution() {

        val (size, virusCnt) = readln().split(" ").map { it.toInt() }

        repeat(size) { rowIndex ->

            val row = (readln().split(" ").mapIndexed { colIndex, element ->
                val value = element.toInt()
                if (value == VIRUS) {
                    virusPositions.add(Position(rowIndex, colIndex))
                    EMPTY
                } else if (value == 1) {
                    -1
                } else {
                    value
                }
            }) as MutableList

            board.add(row)

        }

        dfs(0, 0, virusCnt)

        if (answer == Int.MAX_VALUE) println(-1)
        else println(answer - 1)
    }

    fun dfs(cnt: Int, startIndex: Int, depth: Int) {
        if (cnt == depth) {

            simulate()
            return
        }

        for (index in startIndex until virusPositions.size) {

            selectedVirusIndexList.add(index)
            dfs(cnt + 1, index + 1, depth)
            selectedVirusIndexList.removeLast()

        }

    }

    fun simulate() {

        val queue: Queue<VirusInfo> = LinkedList()
        val copied = board.deepCopy()
        val visited = Array(copied.size) {
            BooleanArray(copied.size)
        }

        selectedVirusIndexList.forEach {
            val pos = virusPositions[it]
            queue.add(VirusInfo(pos, 1))

            copied[pos.x][pos.y] = 1
            visited[pos.x][pos.y] = true
        }

        while (queue.isNotEmpty()) {

            val (pos, cost) = queue.poll()

            dirs.forEach { dir ->

                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                if (nx !in 0 until copied.size ||
                    ny !in 0 until copied.size
                ) return@forEach
                if (visited[nx][ny] || copied[nx][ny] == WALL) return@forEach

                copied[nx][ny] = cost + 1
                queue.add(VirusInfo(Position(nx, ny), cost + 1))
                visited[nx][ny] = true
            }

        }

        if (copied.all { row -> row.all { it != 0 } }) {

            answer = min(
                copied.maxOf { row ->
                    row.maxOf { it }
                }, answer
            )
        }

    }

    private fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> {
        val copied = mutableListOf<MutableList<T>>()
        this.forEach { copied.add(it.toMutableList()) }
        return copied
    }

    companion object {
        const val EMPTY = 0
        const val WALL = -1
        const val VIRUS = 2
    }
}

fun main() {
    전현수_연구소_2().solution()
}