package heejik.`50week`

import kotlin.math.min
import kotlin.properties.Delegates


class `연구소 2` {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    data class Pos(
        val x: Int,
        val y: Int
    )

    var answer = Int.MAX_VALUE
    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val board = mutableListOf<MutableList<Int>>()
    val virusArea = mutableListOf<Pos>()
    val emptyArea = mutableListOf<Pos>()

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) { x ->
            readln().split(' ').map { it.toInt() }.run {
                forEachIndexed { y, value ->
                    val pos = Pos(x, y)
                    if (value == 2) virusArea.add(pos)
                    if (value == 0) emptyArea.add(pos)
                }
                board.add(this.map { if (it == 2) 0 else it }.toMutableList())
            }
        }

        getVirusArea()
        println(if (answer != Int.MAX_VALUE - 1) answer else -1)
    }


    private fun getVirusArea(selectedArea: List<Pos> = emptyList(), idx: Int = 0) {
        if (selectedArea.size == m) {
            bfs(selectedArea).also {
                answer = min(answer, it)
            }
            return
        }
        for (i in idx until virusArea.size) {
            getVirusArea(selectedArea.plus(virusArea[i]), i + 1)
        }
    }

    private fun bfs(posList: List<Pos>): Int {
        val deque = ArrayDeque<Pos>()
        deque.addAll(posList)
        posList.forEach {
            board[it.x][it.y] = 1
        }

        while (deque.isNotEmpty()) {
            val (x, y) = deque.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx in 0 until n && ny in 0 until n && board[nx][ny] == 0) {
                    board[nx][ny] = board[x][y] + 1
                    deque.add(Pos(nx, ny))
                }
            }
        }

        val flattenBoard = board.flatten()
        val count = if (0 in flattenBoard) Int.MAX_VALUE else flattenBoard.max()

        (emptyArea + virusArea).forEach { pos ->
            board[pos.x][pos.y] = 0
        }

        return count - 1
    }
}


fun main() {
    `연구소 2`().solve()
}