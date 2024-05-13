package heejik.`62week`

import java.util.PriorityQueue

class `경쟁적 전염` {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    data class Pos(
        val x: Int,
        val y: Int
    )

    fun solve() {
        val (n, k) = readln().split(' ').map { it.toInt() }
        val board = List(size = n) { MutableList(size = n) { 0 } }
        val store = mutableListOf<Pair<Pos, Int>>()
        val pq = PriorityQueue<Pair<Pos, Int>> { a, b ->
            a.second - b.second
        }
        repeat(n) { x ->
            readln().split(' ').map { it.toInt() }.forEachIndexed { y, virus ->
                board[x][y] = virus
                if (virus != 0) {
                    pq.add(Pos(x, y) to virus)
                }
            }
        }
        val (s, x, y) = readln().split(' ').map { it.toInt() }

        repeat(s) {
            store.clear()
            while (pq.isNotEmpty()) {
                val (pos, virus) = pq.poll()
                for (i in 0..3) {
                    val nx = pos.x + dx[i]
                    val ny = pos.y + dy[i]
                    if (nx !in 0 until n || ny !in 0 until n) continue
                    if (board[nx][ny] != 0) continue
                    board[nx][ny] = virus
                    store.add(Pos(nx, ny) to virus)
                }
            }
            pq.addAll(store)
        }
        println(board[x - 1][y - 1])
    }
}

fun main() {
    `경쟁적 전염`().solve()
}