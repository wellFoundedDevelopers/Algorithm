package hyunsoo.`13week`

/**
 * <문제>
 *
 * [수들의 합 5](https://www.acmicpc.net/problem/2018)
 *
 */
fun main() {

    val target = readln().toInt()

    var start = 1
    var end = 1
    var count = 1

    while (start <= target - 1 && end <= target - 1) {

        val sumOfSubsequence = sumOfRange(start, end + 1)

        if (sumOfSubsequence == target) {
            count++
            start++
        } else if (sumOfSubsequence < target) {
            end++
        } else {
            start++
        }
    }

    println(count)
}

fun sumOfRange(start: Int, end: Int): Int {
    var sum = 0
    for (num in start..end) {
        sum += num
    }
    return sum
}