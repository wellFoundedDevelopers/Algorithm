package hyunsoo.`8week`

import java.util.*

/**
 * <문제>
 * [쇠막대기](https://www.acmicpc.net/problem/10799)
 *
 * 여러 개의 쇠막대기를 레이저로 절단하려고 함.
 *
 */

const val OPEN = '('
const val CLOSE = ')'

fun main() {

    val stack = Stack<Char>()

    val data = readln().trim()

    var pieceCnt = 0

    // 이전 괄호
    var lastParenthesis = data.first()

    stack.add(data.first())

    data.drop(1).forEach { parenthesis ->

        // 현재 괄호에 따른 분기 처리
        when (parenthesis) {
            OPEN -> {
                stack.add(parenthesis)
            }

            CLOSE -> {
                stack.pop()
                // 이전 괄호에 따른 분기 처리
                when (lastParenthesis) {
                    // 이전괄호가 여는 괄호였다면 레이저
                    OPEN -> pieceCnt += stack.size
                    // 이전괄호가 닫는 괄호였다면 막대기의 끝
                    CLOSE -> pieceCnt++
                }
            }
        }
        // 이전괄호 갱신
        lastParenthesis = parenthesis
    }

    println(pieceCnt)

}
