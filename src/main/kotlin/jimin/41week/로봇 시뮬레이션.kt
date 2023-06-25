package jimin.`41week`

import kotlin.system.exitProcess

class `로봇 시뮬레이션` {
    val dx = mutableListOf(-1, 0, 1, 0)
    val dy = mutableListOf(0, 1, 0, -1)

    fun solve() {
        val (b, a) = readln().split(" ").map { it.toInt() }
        val (n, m) = readln().split(" ").map { it.toInt() }
        val robots = mutableListOf<MutableList<Int>>()
        val ground = MutableList(a) { MutableList(b) { -1 } }
        repeat(n) {
            readln().split(" ").map {
                //println("it $it")
                when (it) {
                    "N" -> N
                    "E" -> E
                    "S" -> S
                    "W" -> W
                    else -> it.toInt() - 1
                }
            }.apply {
                //println("this $this")
                robots.add(mutableListOf((a - 1) - this[1], this[0], this[2]))
            }
            ground[robots.last()[0]][robots.last()[1]] = it + 1
        }

        //println("robots $robots")
        repeat(m) {
            val (robot, order, num) = readln().split(" ")
            var (x, y, d) = robots[robot.toInt() - 1]
            if (order == "F") {
                repeat(num.toInt()) {
                    if (x + dx[d] in 0 until a && y + dy[d] in 0 until b) {
                        if (ground[x + dx[d]][y + dy[d]] != -1) {
                            println("Robot ${robot.toInt()} crashes into robot ${ground[x + dx[d]][y + dy[d]]}")
                            exitProcess(0)
                        }
                        x += dx[d]
                        y += dy[d]
                    } else {
                        println("Robot ${robot.toInt()} crashes into the wall")
                        exitProcess(0)
                    }
                }
                ground[robots[robot.toInt() - 1][0]][robots[robot.toInt() - 1][1]] = -1
                ground[x][y] = robot.toInt()
                robots[robot.toInt() - 1][0] = x
                robots[robot.toInt() - 1][1] = y
            } else if (order == "R") {
                robots[robot.toInt() - 1][2] = (d + num.toInt()) % 4
            } else {
                robots[robot.toInt() - 1][2] = kotlin.math.abs(d - num.toInt() % 4) % 4
            }

            //println("robots $robots")
        }

        println("OK")
    }

    private companion object {
        const val N = 0
        const val E = 1
        const val S = 2
        const val W = 3
    }
}

fun main() {
    `로봇 시뮬레이션`().solve()
}


