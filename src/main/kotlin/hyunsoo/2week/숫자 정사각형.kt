package hyunsoo.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 지난 주에 풀었던 필터, 몬스터 트럭과 일맥상통한 문제인듯.
 * n ~ m 크기의 직사각형을 2차원 배열로 입력받아서
 * 꼭짓점에 쓰여있는 수가 모두같은 가장 큰 정사각형을 찾자.
 * n, m 중 더 작은 것을 maxLen이라고 치면
 * - 2 * 2부터 maxLen * maxLen 크기로 주어진 사각형을 탐색을 한 후 조건에 맞는 것을 출력하기
 *      - 1 * 1(1 크기의 정사각형)은 무조건 존재하므로
 *
 */


data class Pos(val x: Int, val y: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (n, m) = this.readLine().split(" ").map { it.toInt() }
    val maxLen = if (n > m) m else n
    val rectangleInfo = mutableListOf<String>()
    var maxSquareSize = 1
    repeat(n) {

        val rectangleWidth = this.readLine()
        rectangleInfo.add(rectangleWidth)
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            for (len in 1..maxLen) {

                // 범위를 초과하면 다음부분을 탐색
                if (i + len >= n || j + len >= m) continue

                // 탐색을 시작할 지점 - 이것을 기준으로 len만큼 더한 부분의 점들을 확인함.
                val startPos = Pos(i, j)

                if (checkVertex(rectangleInfo, startPos, len) && maxSquareSize < (len + 1) * (len + 1)) {
                    maxSquareSize = (len + 1) * (len + 1)
                }

            }
        }
    }

    println(maxSquareSize)


}

fun checkVertex(rectangleInfo: MutableList<String>, startPos: Pos, len: Int): Boolean {

    // 시작 꼭짓점
    val basePos = rectangleInfo[startPos.x][startPos.y]

    // 정사각형의 길이에 위치한 꼭짓점들의 좌표
    val vertexPosList = listOf(
        Pos(0, len),
        Pos(len, 0),
        Pos(len, len)
    )

    // 하나라도 시작 꼭짓점과 다르다면 조건에 만족X
    vertexPosList.forEach { vertexPos ->
        if (basePos != rectangleInfo[startPos.x + vertexPos.x][startPos.y + vertexPos.y]) return false
    }

    return true
}