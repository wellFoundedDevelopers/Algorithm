package hyunsoo.`43week`

/**
 *
 * <문제>
 * [기타리스트](https://www.acmicpc.net/problem/1495)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_기타리스트` {

    fun solution() {

        val (songCnt, startVolume, limit) = readln().split(" ").map { it.toInt() }
        val volumeList = readln().split(" ").map { it.toInt() }
        val dp = Array(songCnt + 1) {
            BooleanArray(limit + 1) { false }
        }

        dp[0][startVolume] = true

        for (i in 0 until songCnt) {
            for (j in 0..limit) {

                if (dp[i][j]) {
                    val min = j - volumeList[i]
                    val max = j + volumeList[i]

                    if (min in 0..limit) dp[i + 1][min] = true
                    if (max in 0..limit) dp[i + 1][max] = true
                }

            }
        }

        println(dp.last().lastIndexOf(true))

    }
}

fun main() {
    전현수_기타리스트().solution()
}