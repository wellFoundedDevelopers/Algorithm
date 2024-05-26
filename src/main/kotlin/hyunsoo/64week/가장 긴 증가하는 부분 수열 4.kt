package hyunsoo.`64week`

/**
 *
 * <문제>
 * [가장 긴 증가하는 부분 수열 4](https://www.acmicpc.net/problem/14002)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_가장_긴_증가하는_부분_수열_4` {

    fun solution() {
        val n = readln().toInt()
        val array = readln().split(" ").map { it.toInt() }
        val dp = Array(n) {
            intArrayOf()
        }

        var answerIndex = 0
        var maxLength = 0

        dp[0] = intArrayOf(array[0])
        for (cur in 0 until n) {

            dp[cur] = intArrayOf(array[cur])

            var lisIndex = cur
            var lisSize = 1

            for (target in 0 until cur) {

                // 앞에 수열의 마지막 값보다 내가 더 크고
                if (array[target] < array[cur]) {
                    // 갱신할 부분 수열의 길이가 더 길 때
                    if (lisSize < dp[target].size + 1) {
                        lisSize = dp[target].size
                        lisIndex = target
                    }
                }
            }

            if (lisIndex != cur) {
                dp[cur] = dp[lisIndex] + array[cur]
            }

            if (maxLength < dp[cur].size) {
                answerIndex = cur
                maxLength = dp[cur].size
            }
        }

        println(maxLength)
        dp[answerIndex].forEach {
            print("$it ")
        }

    }
}

fun main() {
    전현수_가장_긴_증가하는_부분_수열_4().solution()
}