package heejik.`66week`

import kotlin.math.absoluteValue

private class 용액 {

    fun solve() {
        val n = readln().toInt()
        val numbers = readln().split(' ').map { it.toInt() }

        var start = 0
        var end = numbers.lastIndex
        var answerSum = Int.MAX_VALUE
        var answerNumbers = Int.MAX_VALUE to Int.MAX_VALUE

        while (start != end) {
            val sum = numbers[start] + numbers[end]
            if (sum.absoluteValue < answerSum) {
                answerNumbers = numbers[start] to numbers[end]
                answerSum = sum.absoluteValue
            }

            if (sum < 0) {
                start += 1
            } else if (sum > 0) {
                end -= 1
            } else {
                println(answerNumbers.toList().joinToString(" "))
                return
            }
        }
        println(answerNumbers.toList().joinToString(" "))
    }
}


fun main() {
    용액().solve()
}