package hyunsoo.`10week`

/**
 * <문제>
 * [달팽이](https://www.acmicpc.net/problem/1913)
 */

// 하 우 좌 상
val dirList = listOf(
    Pair(1, 0),
    Pair(0, 1),
    Pair(0, -1),
    Pair(-1, 0),
)

val n = readln().toInt()
val target = readln().toInt()
var num = n * n

val snail = Array(n) {
    IntArray(n)
}

fun main() {

    var x = 0
    var y = 0

    var curDir = 0
    snail[x][y] = num--

    while (num >= 1) {

        val (dx, dy) = dirList[curDir]
        while (
            x + dx in 0 until n &&
            y + dy in 0 until n &&
            snail[x + dx][y + dy] == 0
        ) {
            x += dx
            y += dy
            snail[x][y] = num--
            if (num == 0) showSnailAndGetTargetCoordinate()
        }
        curDir = (curDir + 1) % 4
    }

}

fun showSnailAndGetTargetCoordinate() {
    snail.map {
        println(it.joinToString(" "))
    }

    snail.forEachIndexed { index, array ->
        array.indexOf(target).apply {
            if (this != -1) {
                println("${index + 1} ${this + 1}")
            }
        }
    }

    System.exit(0)

}