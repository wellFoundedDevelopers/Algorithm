package hyunsoo.`53week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [구간 합 구하기 5](https://www.acmicpc.net/problem/11660)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_구간_합_구하기_5` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    private val board = mutableListOf<MutableList<Int>>()

    fun solution() {

        val (size, calculateCnt) = readln()
            .split(" ")
            .map { it.toInt() }

        board.add(MutableList<Int>(size + 1) {
            0
        })

        repeat(size) {

            val row = br.readLine().split(" ").map { it.toInt() } as MutableList
            for (index in 1 until size) {
                row[index] = row[index] + row[index - 1]
            }
            board.add((listOf(0) + row) as MutableList)
        }

        for (i in 1..size) {
            for (j in 1..size) {
                board[i][j] = board[i][j] + board[i - 1][j]
            }
        }

        repeat(calculateCnt) {

            val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toInt() }

            bw.write(
                "${board[x2][y2] - board[x1 - 1][y2] - board[x2][y1 - 1] + board[x1 - 1][y1 - 1]}\n"
            )

        }

        bw.flush()
        bw.close()

    }

}

fun main() {
    전현수_구간_합_구하기_5().solution()
}