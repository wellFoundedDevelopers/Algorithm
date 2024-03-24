package hyunsoo.`55week`

/**
 *
 * <문제>
 * [꿀 따기](https://www.acmicpc.net/problem/21758)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_꿀_따기` {

    fun solution() {

        val placeCnt = readln().toInt()

        val honeyInfo = readln().split(" ").map { it.toInt() }
        val honeyInfoPrefixSum = mutableListOf(honeyInfo.first())
        val reversedHoneyInfoPrefixSum = mutableListOf(honeyInfo.last())

        for (i in 1 until honeyInfo.size) {
            honeyInfoPrefixSum.add(honeyInfoPrefixSum[i - 1] + honeyInfo[i])
            reversedHoneyInfoPrefixSum.add(honeyInfo[honeyInfo.size - i - 1] + reversedHoneyInfoPrefixSum[i - 1])
        }
        reversedHoneyInfoPrefixSum.reverse()

        var answer = 0
        // 벌 벌 통
        for (index in 1 until honeyInfo.size - 1) {

            val curHoney = honeyInfoPrefixSum.last() - honeyInfoPrefixSum.first() - honeyInfo[index]
            val curBeeGot = honeyInfoPrefixSum.last() - honeyInfoPrefixSum[index]

            (curHoney + curBeeGot).let { totalHoneyICanGetInThisCase ->
                if (answer < totalHoneyICanGetInThisCase) answer = totalHoneyICanGetInThisCase
            }

        }

        // 벌 통 벌
        for (index in 1 until honeyInfo.size - 1) {

            val firBeeGot = honeyInfoPrefixSum[index] - honeyInfo.first()
            val secBeeGot = reversedHoneyInfoPrefixSum[index] - honeyInfo.last()

            (firBeeGot + secBeeGot).let { totalHoneyICanGetInThisCase ->
                if (answer < totalHoneyICanGetInThisCase) answer = totalHoneyICanGetInThisCase
            }

        }

        // 통 벌 벌
        for (index in 1 until honeyInfo.size - 1) {

            val curHoney = reversedHoneyInfoPrefixSum.first() - reversedHoneyInfoPrefixSum.last() - honeyInfo[index]
            val curBeeGot = reversedHoneyInfoPrefixSum.first() - reversedHoneyInfoPrefixSum[index]

            (curHoney + curBeeGot).let { totalHoneyICanGetInThisCase ->
                if (answer < totalHoneyICanGetInThisCase) answer = totalHoneyICanGetInThisCase
            }
        }

        println(answer)
    }
}

fun main() {
    전현수_꿀_따기().solution()
}