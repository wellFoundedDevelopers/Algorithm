package hyunsoo.`13week`

/**
 * <문제>
 *
 * [수열](https://www.acmicpc.net/problem/2559)
 *
 */

fun main() {

    val (dayCount, sequenceSize) = readln().split(" ").map { it.toInt() }
    val temperatureDataList = readln().split(" ").map { it.toInt() }
    var maxOfSum = -100 * 100_000

    for (startIndex in 0..dayCount - sequenceSize) {
        val sumOfSubsequence = temperatureDataList.subList(startIndex, startIndex + sequenceSize).sum()
        if (maxOfSum < sumOfSubsequence) maxOfSum = sumOfSubsequence
    }

    println(maxOfSum)

}
