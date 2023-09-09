package hyunsoo.`44week`

/**
 *
 * <문제>
 * [함께 블록 쌓기](https://www.acmicpc.net/problem/18427)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_함께_블록_쌓기` {

    fun solution() {

        val (studentCnt, maxBlock, targetHeight) = readln().split(" ").map { it.toInt() }

        val studentInfo = Array(studentCnt + 1) {
            mutableListOf<Int>()
        }

        repeat(studentCnt) { index ->
            readln().split(" ")
                .map { it.toInt() }
                .forEach { height ->
                    studentInfo[index + 1].add(height)
                }
        }

        val dp = Array(studentCnt + 1) {
            IntArray(targetHeight + 1).apply {
                this[0] = 1
            }
        }

        for (i in 1..studentCnt) {
            for (j in 1..targetHeight) {
                dp[i][j] += dp[i - 1][j]
                dp[i][j] %= 10007

                studentInfo[i].forEach { blockHeight ->
                    val target = j - blockHeight
                    if (target < 0) return@forEach
                    dp[i][j] += dp[i - 1][target]
                    dp[i][j] %= 10007
                }
            }
        }

        println(dp[studentCnt][targetHeight] % 10007)
    }
}

fun main() {
    전현수_함께_블록_쌓기().solution()
}