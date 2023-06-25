package hyunsoo.`39week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [봄버맨](https://www.acmicpc.net/problem/16918)
 *
 * - 아이디어
 * 폭탄이 설치된 후를 0부터 세서 정수를 배열에 저장.
 *   - 3이 되면 폭발
 * 빈 곳은 -1로 관리해야징
 *
 * - 트러블 슈팅
 * 아 숫자 0이 아니고 알파벳 O 네요.
 * - 이건 너무하잖아
 *
 */
class `전현수_봄버맨` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    fun solution() {
        val board = mutableListOf<MutableList<Int>>()

        val (row, column, cnt) = readln().split(" ").map { it.toInt() }

        repeat(row) {
            val rowData = readln().chunked(1).map {
                if (it == INIT_EMPTY) -1
                else 1
            } as MutableList

            board.add(rowData)
        }
        var timer = 1
        while (timer < cnt) {

            // 폭탄이 설치되지 않은 곳에 폭탄 설치
            board.tick()
            board.plantBomb()
            timer++
            if (timer == cnt) break

            // 폭발
            board.tick()
            board.bomb()
            timer++
            if (timer == cnt) break

        }

        board.forEach { rowData ->
            rowData.map {
                if (it == EMPTY) "." else "O"
            }.run { println(this.joinToString("")) }
        }

    }

    private fun MutableList<MutableList<Int>>.plantBomb() {
        this.forEach { x, y ->
            val curInfo = this[x][y]
            if (curInfo == EMPTY) this[x][y] = INIT_BOMB
        }
    }

    private fun MutableList<MutableList<Int>>.tick() {
        this.forEach { x, y ->
            val curInfo = this[x][y]
            if (curInfo != EMPTY) this[x][y]++
        }
    }

    private fun MutableList<MutableList<Int>>.bomb() {

        val queue: Queue<Pair<Int, Int>> = LinkedList()

        this.forEach { x, y ->
            val curInfo = this[x][y]
            if (curInfo == READY_BOMB) {
                queue.add(Pair(x, y))
                this[x][y] = EMPTY
            }
        }

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            dirs.forEach { dir ->

                val nx = x + dir.x
                val ny = y + dir.y

                // 범위 초과
                if (nx !in 0 until this.size ||
                    ny !in 0 until this.first().size
                ) {
                    return@forEach
                }

                this[nx][ny] = EMPTY
            }
        }
    }

    private fun MutableList<MutableList<Int>>.forEach(block: (Int, Int) -> Unit) {
        for (i in 0 until this.size) {
            for (j in 0 until this.first().size) {
                block(i, j)
            }
        }
    }

    companion object {
        private const val EMPTY = -1
        private const val INIT_EMPTY = "."
        private const val INIT_BOMB = 0
        private const val READY_BOMB = 3
    }
}

fun main() {
    전현수_봄버맨().solution()
}