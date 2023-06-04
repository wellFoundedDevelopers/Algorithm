package heejik.`35week`

import kotlin.math.pow
import kotlin.properties.Delegates

class `단어 수학` {

    private var n by Delegates.notNull<Int>()
    private val alphaOffset = MutableList(26) { 0 }

    fun solve() {

        setting()
        getMaxSum().also { maxSum ->
            println(maxSum)
        }
    }

    private fun setting() {
        n = readln().toInt()

        repeat(n) {
            val word = readln().reversed()
            word.forEachIndexed { exponent, alpha ->
                alphaOffset[alpha.code - 65] += 10.0.pow(exponent).toInt()
            }
        }
        alphaOffset.sortDescending()
    }

    private fun getMaxSum(): Int {
        var maxSum = 0
        var canOffset = 9

        alphaOffset.forEach { offset ->
            if (offset == 0) return@forEach
            maxSum += offset * canOffset--
        }

        return maxSum
    }
}

fun main() {
    `단어 수학`().solve()
}