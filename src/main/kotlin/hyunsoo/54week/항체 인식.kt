package hyunsoo.`54week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [항체 인식](https://www.acmicpc.net/problem/22352)
 *
 * - 아이디어
 * 영역 별 구분되어있는 모양이 같아야 함.
 * 그리고 하나의 영역을 제외하고는 숫자가 같아야 함.
 *
 * - 트러블 슈팅
 *
 */
class `전현수_항체_인식` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(0, -1),
        Position(-1, 0),
        Position(0, 1),
        Position(1, 0),
    )

    private val origin = mutableListOf<MutableList<Int>>()
    private val expected = mutableListOf<MutableList<Int>>()

    fun solution() {

        // 두 좌표의 숫자가 다른 경우의 수
        var numDifferentCnt = 0

        val (n, m) = readln().split(" ").map { it.toInt() }

        repeat(n) {
            val row = readln().split(" ").map { it.toInt() }
            origin.add(row as MutableList)
        }

        repeat(n) {
            val row = readln().split(" ").map { it.toInt() }
            expected.add(row as MutableList)
        }

        for (i in 0 until origin.size) {
            for (j in 0 until origin.first().size) {

                val curNumForOrigin = origin[i][j]
                val curNumForExpected = expected[i][j]

                if (curNumForOrigin != curNumForExpected) {
                    numDifferentCnt++

                    if (2 <= numDifferentCnt) {
                        println("NO")
                        exitProcess(0)
                    }

                    val visited = Array(n) {
                        BooleanArray(m)
                    }

                    dfs(curNumForOrigin, curNumForExpected, Position(i, j), origin, visited)

                    if (origin.flatten() == expected.flatten()) println("YES")
                    else println("NO")
                    exitProcess(0)
                }
            }
        }

        println("YES")

    }

    private fun dfs(
        targetNum: Int,
        expectNumber: Int,
        targetPos: Position,
        target: MutableList<MutableList<Int>>,
        visited: Array<BooleanArray>,
    ) {

        val curNum = target[targetPos.x][targetPos.y]

        if (targetNum != curNum) return

        visited[targetPos.x][targetPos.y] = true
        target[targetPos.x][targetPos.y] = expectNumber

        dirs.forEach { dir ->

            val nx = targetPos.x + dir.x
            val ny = targetPos.y + dir.y

            if (nx !in 0 until target.size ||
                ny !in 0 until target.first().size ||
                visited[nx][ny]
            ) return@forEach

            dfs(targetNum, expectNumber, Position(nx, ny), target, visited)
        }

    }

    companion object {
        const val CHECKED = -1
    }
}

fun main() {
    전현수_항체_인식().solution()
}