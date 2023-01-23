package hyunsoo.`19week`

/**
 *
 * <문제>
 * [동전 1](https://www.acmicpc.net/problem/2293)
 *
 * n가지 종류의 동전이 있고, 가치의 합이 K원이 되도록 하는 경우의 수를 구해라.
 *
 * - 아이디어
 * 동전의 가치가 적은 순으로 target까지 해당 동전을 반드시 하나 사용한다고 가정하고 값을 dp에 저장.
 *
 * - 트러블 슈팅
 *
 */
class `전현수_동전_1` {

    fun solution() {

        val (coinCnt, target) = readln().split(" ").map { it.toInt() }
        val dp = IntArray(target + 1)
        val coinList = Array(coinCnt) {
            readln().toInt()
        }.sorted()

        dp[0] = 1

        // 동전을 순차적으로 탐색.
        coinList.forEach { coin ->
            // 탐색하고 있는 동전은 반드시 사용한다고 가정
            // 탐색하고 있는 동전은 반드시 사용해야하므로 그보다 작은 동전은 만들 수 없음.
            for (j in coin..target) {
                dp[j] += dp[j - coin]
            }
        }

        println(dp[target])

    }
}

fun main() {
    전현수_동전_1().solution()
}