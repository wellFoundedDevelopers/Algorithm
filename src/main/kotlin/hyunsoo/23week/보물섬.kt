package hyunsoo.`23week`

/**
 *
 * <문제>
 * [보물섬](https://www.acmicpc.net/problem/2589)
 *
 * 거의 1년 전에 처음으로 풀었던 골드문제라고 좋아했던 기억이 있네요... ㅋㅋㅋㅋㅋ
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_보물섬` {

    private data class Position(val x: Int, val y: Int)

    // 북 남 서 동
    private val dirList = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val mapInfo = mutableListOf<MutableList<Int>>()
    private var answer = 0

    fun solution() {

        val (rowCnt, columnCnt) = readln().split(" ").map { it.toInt() }

        repeat(rowCnt) {
            val rowData = readln().map {
                if (it == 'W') OCEAN else LAND
            }
                .toMutableList()
            mapInfo.add(rowData)
        }

        for (i in 0 until rowCnt) {
            for (j in 0 until columnCnt) {
                val curInfo = mapInfo[i][j]

                if (curInfo != OCEAN) {
                    val copiedMap = mapInfo.deepCopy()
                    val curMinCost = findTreasure(i, j, copiedMap)

                    if (answer < curMinCost) answer = curMinCost
                }
            }
        }

        println(answer)

    }

    private fun findTreasure(x: Int, y: Int, map: MutableList<MutableList<Int>>): Int {

        val queue = ArrayDeque<Pair<Position, Int>>().apply {
            this.add(Pair(Position(x, y), 1))
            map[x][y] = 1
        }

        while (queue.isNotEmpty()) {

            val (curPos, cost) = queue.removeFirst()

            dirList.forEach { dir ->
                val nx = curPos.x + dir.x
                val ny = curPos.y + dir.y

                if (nx in 0 until map.size && ny in 0 until map.first().size) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = cost + 1
                        queue.add(Pair(Position(nx, ny), cost + 1))
                    }
                }
            }

        }

        return map.maxOf { row -> row.maxOf { it } } - 1
    }

    private fun <T> Collection<Collection<T>>.deepCopy(): MutableList<MutableList<T>> {
        val deepCopiedList = mutableListOf<MutableList<T>>()
        this.forEach {
            deepCopiedList.add(it.toMutableList())
        }
        return deepCopiedList
    }

    companion object {
        const val LAND = 0
        const val OCEAN = -1
    }
}

fun main() {
    전현수_보물섬().solution()
}