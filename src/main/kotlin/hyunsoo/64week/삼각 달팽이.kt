package hyunsoo.`64week`

/**
 *
 * <문제>
 * [삼각 달팽이](https://school.programmers.co.kr/learn/courses/30/lessons/68645)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_삼각_달팽이` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(1, 0),
        Position(0, 1),
        Position(-1, -1),
    )

    fun solution(n: Int): List<Int> {

        val array = Array(n) {
            IntArray(n) { 0 }
        }

        var x = -1
        var y = 0
        var dirIndex = 0
        var curNum = 1

        // 방향 전환할 때 까지 움직여야할 횟수
        var changeNumCnt = n
        // 움직인 횟수
        var moveCnt = 0

        while (curNum <= n.getCnt()) {

            val curDir = dirs[dirIndex]
            x += curDir.x
            y += curDir.y
            array[x][y] = curNum++
            moveCnt++

            if (moveCnt == changeNumCnt) {
                dirIndex++
                dirIndex %= 3
                moveCnt = 0
                changeNumCnt--
            }

        }
        return array.map { row ->
            row.filter { it != 0 }
        }.flatten()
    }

    private fun Int.getCnt(): Int {
        var target = 0
        for (i in 1..this) target += i
        return target
    }
}

fun main() {
    전현수_삼각_달팽이().solution(6).apply {
        println(this)
    }
}