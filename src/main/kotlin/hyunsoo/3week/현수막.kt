package hyunsoo.`3week`

/**
 * 나름 간단한 BFS문제인 것 같다?
 * 9개의 방향을 모두 탐색하면 될듯!
 * - 연결된 1들은 모두 0으로 바꾸고 1개로 처리하기!
 */

data class Pos(val x: Int, val y: Int)

// 상 하 좌 우 / 좌상 좌하 우상 우하
val searchPosList = listOf(
    Pos(-1, 0),
    Pos(1, 0),
    Pos(0, -1),
    Pos(0, 1),
    Pos(-1, -1),
    Pos(1, -1),
    Pos(-1, 1),
    Pos(1, 1),
)

val banner = mutableListOf<MutableList<Char>>()
var m = 0
var n = 0

fun main() {
    readln().split(" ").map { it.toInt() }.apply {
        m = this[0]
        n = this[1]
    }
    var wordCnt = 0

    repeat(m) {
        val dataRow = readln().split(" ").map { it.first() }.toMutableList()
        banner.add(dataRow)
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (bfs(i, j)) wordCnt++
        }
    }

    println(wordCnt)
}

fun bfs(x: Int, y: Int): Boolean {

    // 0 이면 애초에 글자가 아님.
    if (banner[x][y] == '0') return false

    banner[x][y] = '0'

    searchPosList.forEach { pos ->

        val nx = x + pos.x
        val ny = y + pos.y

        if (nx in 0 until m && ny in 0 until n) bfs(nx, ny)

    }

    return true
}