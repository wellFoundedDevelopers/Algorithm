package hyunsoo.`28week`

/**
 *
 * <문제>
 * [드래곤 커브](https://www.acmicpc.net/problem/15685)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 * 100도 유효한 좌표였구나...
 *
 */
class `전현수_드래곤_커브` {

    private data class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position) = Position(this.x + other.x, this.y + other.y)
    }

    private val dirs = listOf(
        Position(1, 0),
        Position(0, -1),
        Position(-1, 0),
        Position(0, 1),
    )

    private val map = Array(MAP_SIZE) {
        IntArray(MAP_SIZE)
    }

    fun solution() {
        val curveCnt = readln().toInt()

        repeat(curveCnt) {
            var (x, y, d, g) = readln().split(" ").map { it.toInt() }

            val moveList = mutableListOf<Int>().apply {
                add(d)
            }

            repeat(g) {
                moveList.reversed().forEach { dir ->
                    val nextDir = (dir + 1).run {
                        if (this == 4) 0 else this
                    }
                    moveList.add(nextDir)
                }
            }

            map[y][x] = DRAGON_CURVE

            moveList.forEach { dirIndex ->
                x += dirs[dirIndex].x
                y += dirs[dirIndex].y
                map[y][x] = DRAGON_CURVE
            }

        }

        var dragonCurveCnt = 0

        for (i in 0 until MAP_SIZE - 1) {
            for (j in 0 until MAP_SIZE - 1) {
                if (map[i][j] == DRAGON_CURVE && map[i + 1][j] == DRAGON_CURVE
                    && map[i][j + 1] == DRAGON_CURVE && map[i + 1][j + 1] == DRAGON_CURVE
                ) {
                    dragonCurveCnt++
                }
            }
        }

        println(dragonCurveCnt)

    }

    companion object {
        const val DRAGON_CURVE = 1
        const val MAP_SIZE = 101
    }
}

fun main() {
    전현수_드래곤_커브().solution()
}