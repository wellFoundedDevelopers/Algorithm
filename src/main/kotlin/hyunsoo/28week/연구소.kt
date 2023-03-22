package hyunsoo.`28week`

/**
 *
 * <문제>
 * [연구소](https://www.acmicpc.net/problem/14502)
 *
 * - 아이디어
 *
 * 지도의 크기가 최대 8 * 8
 * 64c3 = 41664 완탐 가능
 *
 * - 트러블 슈팅
 *
 */
class `전현수_연구소` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(0, 1),
        Position(0, -1),
        Position(1, 0),
        Position(-1, 0),
    )

    private val map = mutableListOf<MutableList<Int>>()
    private val emptyPositions = mutableListOf<Position>()
    private val installedPositions = mutableListOf<Position>()

    private var maxSafeZoneSize = 0

    fun solution() {
        val (row, column) = readln().split(" ").map { it.toInt() }

        repeat(row) { rowIndex ->
            val rowData = readln().split(" ").map { it.toInt() } as MutableList
            rowData.forEachIndexed { columnIndex, value ->
                if (value == EMPTY) emptyPositions.add(Position(rowIndex, columnIndex))
            }
            map.add(rowData)
        }

        findAllCase()

        println(maxSafeZoneSize)
    }

    private fun findAllCase(cnt: Int = 0, startWith: Int = 0) {
        if (cnt == MAX_INSTALLED_CNT) {
            // 맵 복사후 탐색
            val wallInstalledMap = map.deepCopy().apply {
                installedPositions.forEach { pos ->
                    this[pos.x][pos.y] = 1
                }
            }

            val safeZoneSize = countSafeZone(wallInstalledMap)
            if (maxSafeZoneSize < safeZoneSize) maxSafeZoneSize = safeZoneSize
            return
        }

        for (index in startWith until emptyPositions.size) {
            installedPositions.add(emptyPositions[index])
            findAllCase(cnt + 1, index + 1)
            installedPositions.removeLast()
        }
    }

    private fun countSafeZone(map: MutableList<MutableList<Int>>): Int {

        val visit = Array(map.size) {
            BooleanArray(map.first().size)
        }

        map.forEachIndexed { rowIndex, rowData ->
            rowData.forEachIndexed { columnIndex, curInfo ->
                if (curInfo == VIRUS) {
                    spreadVirus(
                        map,
                        Position(rowIndex, columnIndex), visit
                    )
                }
            }
        }

        return map.sumOf { rowData ->
            rowData.count {
                it == EMPTY
            }
        }
    }

    private fun spreadVirus(map: MutableList<MutableList<Int>>, startPosition: Position, visit: Array<BooleanArray>) {

        val queue = ArrayDeque<Position>().apply {
            add(startPosition)
        }

        while (queue.isNotEmpty()) {

            val pos = queue.removeFirst()

            dirs.forEach { dir ->

                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                // 이미 방문한 곳 or 범위 초과시 탐색 X
                if (nx !in 0 until map.size ||
                    ny !in 0 until map.first().size ||
                    visit[nx][ny]
                ) return@forEach

                if (map[nx][ny] == WALL) return@forEach

                visit[nx][ny] = true
                map[nx][ny] = VIRUS
                queue.add(Position(nx, ny))
            }

        }

    }

    private fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> {
        val new = mutableListOf<MutableList<T>>()
        this.forEach {
            new.add(it.toMutableList())
        }
        return new
    }

    companion object {
        const val EMPTY = 0
        const val WALL = 1
        const val VIRUS = 2
        const val MAX_INSTALLED_CNT = 3
    }
}

fun main() {
    전현수_연구소().solution()
}