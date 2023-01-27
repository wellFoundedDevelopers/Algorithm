package hyunsoo.`20week`

/**
 *
 * <문제>
 * [안전 영역](https://www.acmicpc.net/problem/2468)
 *
 * - 아이디어
 * DFS / BFS인데
 * DFS로 풀어봐야징
 *
 * 최소 높이 - 1(아무 곳도 잠기지 않는 경우) 와 최대 높이까지 잠겼을 때를 DFS로 탐색하자.
 * - 비에 잠기는 높이별로 이차원 배열을 깊은 복사하여 관리한다.
 *
 * - 트러블 슈팅
 *
 */
class `전현수_안전_영역` {

    private val zoneData = mutableListOf<List<Int>>()

    private data class Position(val x: Int, val y: Int)

    // 상 하 좌 우
    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1)
    )

    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)

    fun solution() {

        val size = readln().toInt()
        val safeZoneCounts = mutableListOf<Int>()

        repeat(size) {
            zoneData.add(
                readln().split(" ").map { it.toInt() }.toList()
            )
        }

        val (minZoneHeight, maxZoneHeight) = getMinAndMax(zoneData)

        for (rainHeight in minZoneHeight - 1..maxZoneHeight) {
            val safeZoneData = getSafeZoneData(rainHeight)
            val safeZoneCnt = countSafeZone(safeZoneData)
            safeZoneCounts.add(safeZoneCnt)
        }

        println(safeZoneCounts.maxOf { it })

    }

    private fun countSafeZone(safeZone: List<MutableList<Int>>): Int {
        var safeZoneCnt = 0

        for (i in safeZone.indices) {
            for (j in safeZone.indices) {
                if (dfs(i, j, safeZone)) safeZoneCnt++
            }
        }

        return safeZoneCnt
    }

    private fun dfs(x: Int, y: Int, safeZone: List<MutableList<Int>>): Boolean {

        if (safeZone[x][y] == 0) return false

        safeZone[x][y] = 0

        dirs.forEach { nextPos ->

            val nx = x + nextPos.x
            val ny = y + nextPos.y

            if (nx in safeZone.indices && ny in safeZone.indices) {
                dfs(nx, ny, safeZone)
            }
        }

        return true

    }

    private fun getMinAndMax(targetData: List<List<Int>>): Pair<Int, Int> {
        var min = 0
        var max = 101

        for (i in targetData.indices) {
            for (j in targetData.indices) {

                val curValue = targetData[i][j]

                if (curValue < min) min = curValue
                if (max < curValue) max = curValue
            }
        }

        return Pair(min, max)
    }

    private fun getSafeZoneData(rainHeight: Int): List<MutableList<Int>> {
        return zoneData.deepCopy().map { list ->
            list.map { zoneHeight -> if (zoneHeight <= rainHeight) 0 else zoneHeight }
                .toMutableList()
        }
    }

    private fun <T> Collection<Collection<T>>.deepCopy(): List<List<T>> {
        val deepCopiedList = mutableListOf<List<T>>()
        this.forEach { list ->
            deepCopiedList.add(list.toList())
        }
        return deepCopiedList
    }
}

fun main() {
    전현수_안전_영역().solution()
}