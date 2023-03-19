package hyunsoo.`27week`

import java.io.BufferedWriter
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [팰린드롬?](https://www.acmicpc.net/problem/10942)
 *
 * - 아이디어
 *
 * dp[s][e] 에는 전체 문자열 s 인덱스부터 e 인덱스까지의 펠린드롬 여부를 저장
 * 판단 기준:
 * 2 ~ 5 인덱스의 문자열이 펠린드롬인지 확인하고 싶을 경우
 *   - 3 ~ 4 인덱스가 펠린드롬이 아닐경우 펠린드롬이 될 수가 없음
 *   - 펠린드롬이 맞다면 2, 5가 같은 수 일 경우 팰린드롬
 *
 * - 트러블 슈팅
 *
 * 탑다운 방식으로 진행하였음.
 *
 */
class `전현수_펠린드롬` {

    fun solution() {

        val bw = BufferedWriter(OutputStreamWriter(System.out))

        val sequenceSize = readln().toInt()
        val sequence = listOf(0) + readln().split(" ").map { it.toInt() }
        val qCnt = readln().toInt()

        val dp = Array(sequenceSize + 1) {
            IntArray(sequenceSize + 1) { NO }
        }

        for (i in 0..sequenceSize) {
            dp[i][i] = YES
        }

        for (i in 1 until sequenceSize) {
            if (sequence[i] == sequence[i + 1]) dp[i][i + 1] = YES
        }

        for (i in sequenceSize downTo 1) {
            for (j in sequenceSize downTo i + 1) {
                if (dp[i + 1][j - 1] == YES && sequence[i] == sequence[j]) dp[i][j] = YES
            }
        }

        repeat(qCnt) {
            val (s, e) = readln().split(" ").map { it.toInt() }
            bw.write("${dp[s][e]}\n")
        }

        bw.flush()
        bw.close()

    }

    companion object {
        const val YES = 1
        const val NO = 0
    }
}

fun main() {
    전현수_펠린드롬().solution()
}