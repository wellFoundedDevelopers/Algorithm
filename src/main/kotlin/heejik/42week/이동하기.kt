package heejik.`42week`

import kotlin.math.max

class 이동하기 {

    fun solve() {
        val maze = mutableListOf<MutableList<Int>>()
        val (n, m) = readln().split(' ').map { it.toInt() }
        repeat(n) {
            maze.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        maze.forEachIndexed { x, row ->
            row.forEachIndexed { y, candy ->
                var maxPreCandy = 0
                if (x - 1 >= 0) {
                    maxPreCandy = maze[x - 1][y]
                }
                if (y - 1 >= 0) {
                    maxPreCandy = max(maze[x][y - 1], maxPreCandy)
                }
                if (x - 1 >= 0 && y - 1 >= 0) {
                    maxPreCandy = max(maze[x - 1][y - 1], maxPreCandy)
                }

                maze[x][y] += maxPreCandy
            }
        }

        println(maze[n - 1][m - 1])
    }
}


fun main() {
    이동하기().solve()
}