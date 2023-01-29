package hyunsoo.`20week`

/**
 *
 * <문제>
 * [양](https://www.acmicpc.net/problem/3184)
 *
 * 재미게따~~
 *
 * - 아이디어
 *
 * dfs로 ., o, v 일 때 탐색해서
 * 양의 수, 늑대의 수 반환해서 비교해보기
 *
 * - 트러블 슈팅
 *
 */
class `전현수_양` {

    data class Direction(val x: Int, val y: Int)

    private val dirList = listOf(
        Direction(-1, 0),
        Direction(1, 0),
        Direction(0, -1),
        Direction(0, 1),
    )

    private val FENSE = '#'
    private val WOLF = 'v'
    private val SHEEP = 'o'
    private val NONE = '.'
    private var row = 0
    private var column = 0

    private val mapInfo = mutableListOf<MutableList<Char>>()

    private var wolfCountResult = 0
    private var sheepCountResult = 0

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            row = this.first()
            column = this.last()
        }

        repeat(row) {
            mapInfo.add(
                readln().toMutableList()
            )
        }

        for (i in 0 until row) {
            for (j in 0 until column) {

                val (wolfCount, sheepCount) = countAnimals(i, j)

                if (wolfCount < sheepCount) {
                    sheepCountResult += sheepCount
                } else {
                    wolfCountResult += wolfCount
                }

            }
        }

        println("$sheepCountResult $wolfCountResult")
    }

    private fun countAnimals(x: Int, y: Int): Pair<Int, Int> {

        val curInfo = mapInfo[x][y]
        var wolfCount = 0
        var sheepCount = 0

        when (curInfo) {
            FENSE -> {
                return Pair(wolfCount, sheepCount)
            }

            WOLF -> {
                wolfCount++
                mapInfo[x][y] = NONE
            }

            SHEEP -> {
                sheepCount++
                mapInfo[x][y] = NONE
            }
        }
        mapInfo[x][y] = FENSE

        dirList.forEach { dir ->

            val nx = x + dir.x
            val ny = y + dir.y

            if (nx in 0 until row && ny in 0 until column) {
                val (linkedWolfCount, linkedSheepCount) = countAnimals(nx, ny)
                wolfCount += linkedWolfCount
                sheepCount += linkedSheepCount
            }

        }

        return Pair(wolfCount, sheepCount)

    }
}

fun main() {
    전현수_양().solution()
}