package hyunsoo.`4week`

/**
 *
 * <문제>
 * [그림](https://www.acmicpc.net/problem/1926)
 *
 * 이것도 DFS 문제!
 * 상하좌우를 탐색.
 * 연결되어있는 1의 개수를 합칠거야.
 */
val paintData by lazy { mutableListOf<MutableList<Int>>() }

val dirList = listOf(
    Pair(-1, 0),
    Pair(1, 0),
    Pair(0, 1),
    Pair(0, -1),
)

fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }
    val madePaintSize = mutableListOf<Int>()

    repeat(n) {
        val rowData = readln().split(" ").map { it.toInt() }.toMutableList()
        paintData.add(rowData)
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            val curPaintSize = findPaint(i, j, 0)
            if (curPaintSize > 0) madePaintSize.add(curPaintSize)
        }
    }

    madePaintSize.apply {
        if (size == 0) {
            println(0)
            println(0)
            return
        }
        println(size)
        println(maxOf { it })
    }
}

fun findPaint(x: Int, y: Int, cnt: Int): Int {

    var size = cnt
    if (paintData[x][y] == 0) return 0

    paintData[x][y] = 0
    size++

    dirList.forEach { dir ->
        val nx = x + dir.first
        val ny = y + dir.second
        val maxX = paintData.size
        val maxY = paintData.first().size

        if (nx in 0 until maxX &&
            ny in 0 until maxY
        ) {
            size += findPaint(nx, ny, 0)
        }
    }

    return size
}