package hyunsoo.`13week`

/**
 * <문제>
 *
 * [수들의 합 2](https://www.acmicpc.net/problem/2003)
 *
 * 투 포인터 연습해볼라고요
 */

fun main() {

    val (numCount, target) = readln().split(" ").map { it.toInt() }
    val numList = readln().split(" ").map { it.toInt() }

    var start = 0
    var end = 0
    var count = 0

    while(start != numCount || end != numCount) {

        val sumOfSubsequence = numList.subList(start, end).sum()

        if(sumOfSubsequence < target) {
            end++
        } else if (sumOfSubsequence == target) {
            count++
            start++
        } else {
            start++
        }

        if(start > numCount || end > numCount) break
    }

    println(count)
}

