package byeonghee.week41

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_로봇시뮬레이션 {

    companion object {
        const val N = 0
        const val E = 1
        const val S = 2
        const val W = 3

        const val R = 1
        const val L = 3
        const val F = 0

        val dy = intArrayOf(1, 0, -1, 0)
        val dx = intArrayOf(0, 1, 0, -1)

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (width, height) = readLine().split(" ").map { it.toInt() }
            val (n, m) = readLine().split(" ").map { it.toInt() }

            val ground = Array(height + 1) { IntArray(width + 1) }
            val robots = Array(n+1) { IntArray(3) }

            for(i in 1..n) {
                readLine().split(" ").forEachIndexed { j, v -> robots[i][j] = strToDir(v) }
                ground[robots[i][1]][robots[i][0]] = i
            }

            repeat(m) {
                val (id, order, times) = readLine().split(" ").map { strToDir(it) }
                val (x, y, d) = robots[id]

                when(order) {
                    R, L -> {
                        robots[id][2] = (d + order * times) % 4
                    }
                    else -> {
                        ground[y][x] = 0
                        var ny = y
                        var nx = x
                        repeat(times) {
                            ny += dy[d]
                            nx += dx[d]
                            if (ny !in 1..height || nx !in 1..width) {
                                println("Robot $id crashes into the wall")
                                return@with
                            }
                            if (ground[ny][nx] != 0) {
                                println("Robot $id crashes into robot ${ground[ny][nx]}")
                                return@with
                            }
                        }
                        ground[ny][nx] = id
                        robots[id][1] = ny
                        robots[id][0] = nx
                    }
                }
            }

            println("OK")
        }

        fun strToDir(str: String) =
            when(str) {
                "N" -> N
                "E" -> E
                "S" -> S
                "W" -> W
                "R" -> R
                "L" -> L
                "F" -> F
                else -> str.toInt()
            }
    }
}

fun main() {
    소병희_로봇시뮬레이션.solve()
}