package hyunsoo.`4week`

/**
 * DFS로 1그룹을 묶어서 크기를 반환하자.
 *
 */

val mapSize = readln().toInt()
val mapData = mutableListOf<MutableList<Int>>()

val searchDir = listOf(
    Pair(-1, 0),
    Pair(1, 0),
    Pair(0, -1),
    Pair(0, 1)
)

fun main() {

    val complexList = mutableListOf<Int>()
    repeat(mapSize) {
        val data = readln().chunked(1).map { it.toInt() }.toMutableList()
        mapData.add(data)
    }

    for (i in 0 until mapSize) {
        for (j in 0 until mapSize) {
            findComplexAndReturnSize(i, j, 0).let {
                if (it != 0) complexList.add(it)
            }
        }
    }

    complexList.sorted().apply {
        println(size)
        this.forEach {
            println(it)
        }
    }

}

fun findComplexAndReturnSize(x: Int, y: Int, size: Int): Int {

    if (mapData[x][y] == 0) return 0

    var size = size

    mapData[x][y] = 0
    size++

    searchDir.forEach {

        val nx = x + it.first
        val ny = y + it.second

        if (nx in 0 until mapSize && ny in 0 until mapSize) {
            size += findComplexAndReturnSize(nx, ny, 0)
        }
    }

    return size
}