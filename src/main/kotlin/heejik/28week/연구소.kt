package heejik.`28week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.properties.Delegates

class 연구소 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()

    val board = mutableListOf<MutableList<Int>>()
    val emptyPoses = mutableListOf<Pos>()
    val virusPoses = mutableListOf<Pos>()
    var safeAreaCount = 0

    fun solve() {
        getInput()
        setNewWall(listOf())
        println(safeAreaCount)
    }

    private fun getInput() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) { x ->
            board.add(readln().split(' ').map { it.toInt() }.run {
                forEachIndexed { y, it ->
                    if (it == 0) emptyPoses.add(Pos(x, y))
                    if (it == 2) virusPoses.add(Pos(x, y))
                }
                toMutableList()
            })
        }
    }

    private fun setNewWall(wallPos: List<Pos>, start: Int = 0) {
        if (wallPos.size == 3) {
            buildWall(wallPos = wallPos)
            return
        }

        for (i in start until emptyPoses.size) {
            setNewWall(wallPos.plus(emptyPoses[i]), i + 1)
        }
    }

    private fun buildWall(wallPos: List<Pos>) {
        wallPos.forEach { pos ->
            board[pos.x][pos.y] = 1
        }
        spreadVirus()

        wallPos.forEach { pos ->
            board[pos.x][pos.y] = 0
        }
    }

    private fun spreadVirus() {
        val queue = ArrayDeque<Pos>()
        virusPoses.forEach {
            queue.add(it)
        }

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            for (i in dx.indices) {
                val nx = dx[i] + pos.x
                val ny = dy[i] + pos.y
                if ((nx !in 0 until n) or (ny !in 0 until m)) continue

                if (board[nx][ny] == 0) {
                    queue.add(Pos(nx, ny))
                    board[nx][ny] = -1
                }
            }
        }

        getSafeAreaCount()
    }

    private fun getSafeAreaCount() {
        var cnt = 0

        board.forEachIndexed { x, row ->
            row.forEachIndexed { y, it ->
                if (it == 0) cnt++
                if (it == -1) board[x][y] = 0
            }
        }
        safeAreaCount = max(safeAreaCount, cnt)
    }
}

fun main() {
    연구소().solve()
}