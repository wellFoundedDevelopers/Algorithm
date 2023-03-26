package heejik.`28week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.properties.Delegates

class `미세먼지 안녕!` {

    val br = BufferedReader(InputStreamReader(System.`in`))
    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var r by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    var t by Delegates.notNull<Int>()
    val board = mutableListOf<MutableList<Int>>()
    val airCleanerPos = mutableListOf<Pos>()

    fun solve() {
        getInput()
        repeat(t) {
            spread()
            operateAirCleaner()
        }
        println(getDust())
    }

    private fun getInput() {
        br.readLine().split(' ').map { it.toInt() }.run {
            r = this[0]
            c = this[1]
            t = this[2]
        }
        repeat(r) { x ->
            board.add(br.readLine().split(' ').map { it.toInt() }.run {
                forEachIndexed { y, i ->
                    if (i == -1)
                        airCleanerPos.add(Pos(x, y))
                }
                toMutableList()
            })
        }
    }

    private fun spread() {
        val afterBoard = MutableList(r) { MutableList(c) { 0 } }

        board.forEachIndexed { x, row ->
            row.forEachIndexed { y, mount ->
                if (mount == -1) afterBoard[x][y] = -1
                else if (mount != 0) {
                    val spreadPos = mutableListOf<Pos>()
                    for (i in dx.indices) {
                        val nx = x + dx[i]
                        val ny = y + dy[i]
                        if ((nx !in 0 until r) or (ny !in 0 until c)) continue
                        if (board[nx][ny] == -1) continue
                        spreadPos.add(Pos(nx, ny))
                    }
                    spreadPos.forEach { pos ->
                        afterBoard[pos.x][pos.y] += mount / 5
                    }
                    afterBoard[x][y] += mount - ((mount / 5) * spreadPos.size)
                }
            }
        }
        afterBoard.forEachIndexed { x, row ->
            row.forEachIndexed { y, i ->
                board[x][y] = i
            }
        }
    }

    private fun operateAirCleaner() {
        val topCleaner = airCleanerPos.first()
        var tmp = 0
        var preValue = 0
        for (y in 1 until c) {
            tmp = board[topCleaner.x][y]
            board[topCleaner.x][y] = preValue
            preValue = tmp
        }
        for (x in topCleaner.x - 1 downTo 0) {
            tmp = board[x][c - 1]
            board[x][c - 1] = preValue
            preValue = tmp
        }

        for (y in c - 2 downTo 0) {
            tmp = board[0][y]
            board[0][y] = preValue
            preValue = tmp
        }

        for (x in 1 until topCleaner.x) {
            tmp = board[x][0]
            board[x][0] = preValue
            preValue = tmp
        }


        val bottomCleaner = airCleanerPos.last()

        preValue = 0
        for (y in 1 until c) {
            tmp = board[bottomCleaner.x][y]
            board[bottomCleaner.x][y] = preValue
            preValue = tmp
        }
        for (x in bottomCleaner.x + 1 until r) {
            tmp = board[x][c - 1]
            board[x][c - 1] = preValue
            preValue = tmp
        }

        for (y in c - 2 downTo 0) {
            tmp = board[r - 1][y]
            board[r - 1][y] = preValue
            preValue = tmp
        }

        for (x in r - 2 downTo bottomCleaner.x + 1) {
            tmp = board[x][0]
            board[x][0] = preValue
            preValue = tmp

        }
    }

    private fun getDust(): Int = board.sumOf { it.sum() } + 2
}

fun main() {
    `미세먼지 안녕!`().solve()
}