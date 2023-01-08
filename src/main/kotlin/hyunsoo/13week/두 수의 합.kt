package hyunsoo.`13week`

/**
 * <문제>
 *
 * [두 수의 합](https://www.acmicpc.net/problem/3273)
 */

fun main() {

    val arraySize = readln().toInt()
    val numList = readln().split(" ").map { it.toInt() }.sorted()
    val targetNum = readln().toInt()

    var start = 0
    var end = numList.lastIndex
    var count = 0

    while (start < end) {

        val sumOfPair = numList[start] + numList[end]

        if (sumOfPair == targetNum) {
            count++
            start++
        } else if (sumOfPair < targetNum) {
            start++
        } else {
            end--
        }

    }

    println(count)
}