package heejik.`45week`

import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

data class Pos(
    val x: Int,
    val y: Int
)

data class Area(
    var type: Int,  // 0 -> 안전, 1 -> 위험, 2-> 죽음
    var lostLife: Int
)

class 게임 {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val board = MutableList(501) { MutableList(501) { Area(type = 0, lostLife = 0) } }
    val visited = MutableList(501) { MutableList(501) { false } }


    fun play() {
        setting()
        move(_pos = Pos(x = 0, y = 0))
        println(if (board[500][500].lostLife == Int.MAX_VALUE) -1 else board[500][500].lostLife)
    }

    private fun setting() {
        board[500][500].lostLife = Int.MAX_VALUE

        n = readln().toInt()
        repeat(n) {
            val (x1, y1, x2, y2) = readln().split(' ').map { it.toInt() }
            val minX = min(x1, x2)
            val maxX = max(x1, x2)
            val minY = min(y1, y2)
            val maxY = max(y1, y2)

            for (i in minX..maxX) {
                for (j in minY..maxY) {
                    board[i][j].type = 1
                }
            }
        }

        m = readln().toInt()
        repeat(m) {
            val (x1, y1, x2, y2) = readln().split(' ').map { it.toInt() }
            val minX = min(x1, x2)
            val maxX = max(x1, x2)
            val minY = min(y1, y2)
            val maxY = max(y1, y2)

            for (i in minX..maxX) {
                for (j in minY..maxY) {
                    board[i][j].type = 2
                }
            }
        }
    }

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)
    private fun move(_pos: Pos) {
        val queue = ArrayDeque<Pos>()
        queue.add(_pos)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                val originLostLife = board[x][y].lostLife

                if (nx in 0 until 501 && ny in 0 until 501) {
                    val nextType = board[nx][ny].type
                    val nextLostLife = board[nx][ny].lostLife

                    if (nextType == 2) continue
                    else if (nextType == 1) {
                        if (visited[nx][ny].not()) {
                            visited[nx][ny] = true
                            queue.add(Pos(nx, ny))
                            board[nx][ny].lostLife = originLostLife + 1
                        } else if (originLostLife + 1 < nextLostLife) {
                            queue.add(Pos(nx, ny))
                            board[nx][ny].lostLife = originLostLife + 1
                        }
                    } else if (nextType == 0) {
                        if (visited[nx][ny].not()) {
                            visited[nx][ny] = true
                            queue.add(Pos(nx, ny))
                            board[nx][ny].lostLife = originLostLife
                        } else if (originLostLife < nextLostLife) {
                            queue.add(Pos(nx, ny))
                            board[nx][ny].lostLife = originLostLife
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    게임().play()
}