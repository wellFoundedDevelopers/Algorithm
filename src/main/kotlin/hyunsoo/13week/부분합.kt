package hyunsoo.`13week`

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * <문제>
 *
 * [부분합](https://www.acmicpc.net/problem/1806)
 */

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val (sequenceLength, target) = br.readLine().split(" ").map { it.toInt() }
    val prefixSumSequence = mutableListOf<Int>(0)
    val sequence = br.readLine().split(" ").map { it.toInt() }.fold(0) { total, next ->
        prefixSumSequence.add(total + next)
        total + next
    }

    var start = 0
    var end = 0
    var minLength = 100000

    while (start <= sequenceLength && end <= sequenceLength) {

        val sumOfSubsequence = prefixSumSequence[end] - prefixSumSequence[start]

        if (sumOfSubsequence >= target) {
            start++
            val subsequenceLength = end - start + 1
            if (subsequenceLength < minLength) minLength = subsequenceLength
        } else {
            end++
        }
    }

    println(if (minLength == 100000) 0 else minLength)

}