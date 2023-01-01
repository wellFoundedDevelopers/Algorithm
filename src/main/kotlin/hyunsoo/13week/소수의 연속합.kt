package hyunsoo.`13week`

import kotlin.math.sqrt


/**
 * <문제>
 *
 * [소수의 연속합](https://www.acmicpc.net/problem/1644)
 */

fun main() {

    val target = readln().toInt()
    val primeList = getPrimeList(target)

    var count = 0
    var start = 0
    var end = 0

    while (start <= primeList.lastIndex && end <= primeList.lastIndex) {

        val sumOfSubsequence = primeList.subList(start, end + 1).sum()

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

fun getPrimeList(target: Int): List<Int> {
    val numArray = (0..target).toMutableList()
    val primeList = mutableListOf<Int>()

    for (prime in 2..sqrt(target.toDouble()).toInt()) {
        if (numArray[prime] != 0) {
            for (num in prime + 1..target) {
                if (num % prime == 0) numArray[num] = 0
            }
        }
    }
    numArray.drop(2).forEachIndexed { index, num ->
        if (num != 0) primeList.add(index + 2)
    }

    return primeList

}