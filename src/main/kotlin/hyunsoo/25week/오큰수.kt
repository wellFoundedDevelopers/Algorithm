package hyunsoo.`25week`

import java.util.Stack

/**
 *
 * <문제>
 * [오큰수](https://www.acmicpc.net/problem/17298)
 *
 * - 아이디어
 *
 * 완탐하면 n^2인데 n이 1,000,000이네...
 *
 * - 트러블 슈팅
 *
 */
class `전현수_오큰수` {

    private val stack = Stack<Int>()

    fun solution() {

        val sequenceSize = readln().toInt()
        val sequence = readln().split(" ").map { it.toInt() }
        val answerList = IntArray(sequenceSize) { -1 }

        stack.push(0)

        for (index in 1 until sequenceSize) {

            val curNum = sequence[index]
            val lastStackNum = sequence[stack.peek()]

            if (lastStackNum < curNum) {

                while (stack.isNotEmpty()
                    && sequence[stack.peek()] < curNum
                ) {

                    val targetIndex = stack.pop()
                    answerList[targetIndex] = curNum

                }
            }
            stack.push(index)
        }

        println(answerList.joinToString(" "))
    }

}

fun main() {
    전현수_오큰수().solution()
}