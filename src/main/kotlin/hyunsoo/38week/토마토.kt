package hyunsoo.`38week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [토마토](https://www.acmicpc.net/problem/7569)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_토마토` {

    private val br = BufferedReader(InputStreamReader(System.`in`))

    private data class Position(val x: Int, val y: Int, val z: Int)

    private val dirs = listOf(
        Position(-1, 0, 0), // 북
        Position(1, 0, 0), // 남
        Position(0, -1, 0), // 서
        Position(0, 1, 0), // 동
        Position(0, 0, -1), // 아래
        Position(0, 0, 1), // 위
    )

    fun solution() {

        val (m, n, h) = br.readLine().split(" ").map { it.toInt() }
        val queue: Queue<Position> = LinkedList()

        val box = Array(h) {
            Array(n) {
                IntArray(m)
            }
        }

        repeat(h * n) { index ->
            val row = br.readLine().split(" ").map { it.toInt() }.toIntArray()
            box[index / n][index % n] = row
        }

        for (i in 0 until h) {
            for (j in 0 until n) {
                for (k in 0 until m) {
                    val curTomato = box[i][j][k]
                    if (curTomato == DONE) queue.add(Position(i, j, k))
                }
            }
        }

        while (queue.isNotEmpty()) {

            val (i, j, k) = queue.poll()

            dirs.forEach { dir ->

                val (x, y, z) = dir
                val nz = i + z
                val nx = j + x
                val ny = k + y

                if (
                    nz in 0 until h &&
                    nx in 0 until n &&
                    ny in 0 until m &&
                    box[nz][nx][ny] == YET
                ) {
                    box[nz][nx][ny] = box[i][j][k] + 1
                    queue.add(Position(nz, nx, ny))
                }
            }

        }

        if (box.isAllDone()) println(box.max() - 1)
        else println(-1)
    }

    private fun Array<Array<IntArray>>.isAllDone() =
        this.all { twoDimensionArray ->
            twoDimensionArray.all { intArray ->
                intArray.all {
                    it != YET
                }
            }
        }

    private fun Array<Array<IntArray>>.max() =
        this.maxOf { twoDimensionArray ->
            twoDimensionArray.maxOf { intArray ->
                intArray.maxOf {
                    it
                }
            }
        }


    companion object {
        private const val DONE = 1
        private const val YET = 0
        private const val EMPTY = -1
    }
}

fun main() {
    전현수_토마토().solution()
}