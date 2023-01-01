package hyunsoo.`13week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 *
 * [두 용액](https://www.acmicpc.net/problem/2470)
 */

data class Solution(val firSolution: Int, val secSolution: Int) {
    override fun toString(): String {
        return if (firSolution < secSolution) "$firSolution $secSolution" else "$secSolution $firSolution"
    }
}

val solutionCnt = readln()
val solutionList = readln().split(" ").map { it.toInt() }.sorted()

var numThatCloseToZero = Int.MAX_VALUE
var start = 0
var end = solutionList.lastIndex
var ans = Solution(0, 0)

fun main() {

    while (start < end) {

        val sumOfSolution = solutionList[start] + solutionList[end]

        if (sumOfSolution < 0) {
            renewCloserToZeroWord(sumOfSolution)
            start++
        } else if (sumOfSolution > 0) {
            renewCloserToZeroWord(sumOfSolution)
            end--
        } else {
            println(Solution(solutionList[start], solutionList[end]))
            return
        }

    }
    println(ans)

}

fun renewCloserToZeroWord(sumOfSolution: Int) {
    if (sumOfSolution.absoluteValue < numThatCloseToZero.absoluteValue) {
        numThatCloseToZero = sumOfSolution
        ans = Solution(solutionList[start], solutionList[end])
    }
}