package heejik.`7week`

import kotlin.math.abs

class `미로 만들기` {
    // 위, 오른, 아래, 왼
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)

    data class Pos(
        var x: Int,
        var y: Int,
    )

    fun solve() {
        var direction = 2     // 아래(남)
        var pos = Pos(0, 0)
        var maze = mutableListOf(pos)
        val n = readln().toInt()
        val move = readln()
        move.forEach {
            when (it) {
                'R' -> {
                    direction = (direction + 1) % 4
                }

                'L' -> {
                    direction = (direction - 1).run { if (this < 0) 4 + this else this}
                }

                'F' -> {
                    pos = Pos(pos.x + dx[direction], pos.y + dy[direction])
                    maze.add(pos)
                }
            }
        }
        val offSetX = maze.minOf { it.x }
        val offSetY = maze.minOf { it.y }

        if (offSetX < 0) {
            maze = maze.map {
                Pos(it.x + abs(offSetX), it.y)
            }.toMutableList()
        }
        if (offSetY < 0) {
            maze = maze.map {
                Pos(it.x, it.y + abs(offSetY))
            }.toMutableList()
        }
        for (i in 0..maze.maxOf { it.x }) {
            for (j in 0..maze.maxOf { it.y }) {
                if (Pos(i, j) in maze) {
                    print('.')
                } else {
                    print('#')
                }
            }
            println()
        }
    }
}


fun main() {
//    println(-1%4)
    `미로 만들기`().solve()
}