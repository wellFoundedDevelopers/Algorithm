package hyunsoo.`7week`

/**
 * <문제>
 * [지름길](https://www.acmicpc.net/problem/1446)
 *
 * 매일 아침, 세준이는 학교에 가기 위해 차를 타고 D 킬로미터 길이의 고속도로를 지남
 * 어느 날 지름길이 존재하는 것을 알게 되었고, 지름길은 일방통행임
 * 세준이가 운전해야 하는 거리의 최솟값을 구해라
 *
 * 입/출력
 * - 첫째 줄
 *   - 지름길의 개수 N(12 이하), 고속도로의 길이 D(10,000 이하)
 * - N개의 줄
 *   - 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어짐.(10,000 이하)
 *
 * 아이디어
 * - dp 혹은 다익스트라
 *
 */

data class ShortCutData(val start: Int, val distance: Int)

fun main() {

    val (shortCutCnt, highWayLength) = readln().split(" ").map { it.toInt() }
    val dp = Array(10001) { i -> i }
    val shortCutGraph = Array(10001) {
        mutableListOf<ShortCutData>()
    }

    repeat(shortCutCnt) {
        val (start, end, shortCutDistance) = readln().split(" ").map { it.toInt() }
        // 도착지점이 고속도로의 길이를 넘거나 지름길이 도로보다 길지 않은 경우에만 지름길 생성
        if ((end > highWayLength).not() &&
            (end - start <= shortCutDistance).not()
        ) shortCutGraph[end].add(ShortCutData(start, shortCutDistance))

    }

    for (end in 1..highWayLength) {
        dp[end] = dp[end - 1] + 1
        if (shortCutGraph[end].isNotEmpty()) {
            shortCutGraph[end].forEach { shortCutData ->
                dp[end] = Math.min(dp[end], dp[shortCutData.start] + shortCutData.distance)
            }
        }
    }

    println(dp[highWayLength])
}