package hyunsoo.`63week`

import java.util.Stack

/**
 *
 * <문제>
 * [괄호 제거](https://www.acmicpc.net/problem/2800)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_괄호_제거` {

    private data class ParenthesisInfo(val start: Int, val end: Int)

    private val parenthesisInfoList = mutableListOf<ParenthesisInfo>()
    private val pickedIndexList = mutableListOf<Int>()

    private lateinit var target: String

    private val answerList = mutableSetOf<String>()

    fun solution() {

        target = readln()

        findParenthesisInfo()

        for (i in 1..parenthesisInfoList.size) {
            dfs(i, 0, 0)
        }

        answerList.sorted().forEach {
            println(it)
        }

    }

    private fun findParenthesisInfo() {

        val stack = Stack<Int>()

        for (i in target.indices) {
            if (target[i] == '(') {
                stack.add(i)
            } else if (target[i] == ')') {
                if (stack.isNotEmpty()) {
                    parenthesisInfoList.add(
                        ParenthesisInfo(stack.pop(), i)
                    )
                }
            }
        }
    }

    private fun dfs(cnt: Int, cur: Int, startIndex: Int) {
        if (cnt == cur) {

            val tempSet = mutableSetOf<Int>()
            pickedIndexList.forEach { index ->
                parenthesisInfoList[index].let { parenthesis ->
                    tempSet.add(parenthesis.start)
                    tempSet.add(parenthesis.end)
                }
            }

            val sb = StringBuilder()
            for (i in target.indices) {
                if (i !in tempSet) sb.append(target[i])
            }

            answerList.add(sb.toString())
            return
        }

        for (index in startIndex until parenthesisInfoList.size) {
            pickedIndexList.add(index)
            dfs(cnt, cur + 1, index + 1)
            pickedIndexList.removeLast()
        }
    }
}

fun main() {
    전현수_괄호_제거().solution()
}