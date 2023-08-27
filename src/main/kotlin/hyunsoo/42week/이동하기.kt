package hyunsoo.`42week`

import kotlin.math.max

/**
 *
 * <문제>
 * [이동하기](https://www.acmicpc.net/problem/11048)
 *
 * - 아이디어
 *
 * 당연히 bfs 겠구나 가 아니고 dp네
 *
 * - 트러블 슈팅
 *
 */
class `전현수_이동하기` {

    private data class Position(val x: Int, val y: Int)


    private val dirs = listOf(
        Position(-1, 0),
        Position(0, -1),
        Position(-1, -1),
    )

    private val maze = mutableListOf<List<Int>>()

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }
        val dp = Array(n) {
            IntArray(m)
        }

        repeat(n) {
            readln().split(" ").map { it.toInt() }.apply {
                maze.add(this)
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {

                var maxValue = maze[i][j]

                dirs.forEach { dir ->
                    val nx = i + dir.x
                    val ny = j + dir.y
                    if (nx !in 0 until n || ny !in 0 until m) return@forEach

                    maxValue = max(maxValue, maze[i][j] + dp[nx][ny])

                }

                dp[i][j] = maxValue

            }
        }

        println(dp[n - 1][m - 1])

    }
}

fun main() {
    전현수_이동하기().solution()
}