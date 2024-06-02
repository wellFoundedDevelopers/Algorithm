package hyunsoo.`65week`

import java.util.*

/**
 *
 * <문제>
 * [후위 표기식](https://www.acmicpc.net/problem/1918)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_후위_표기식` {

    fun solution() {

        val s = readln()
        val sb = StringBuilder()
        val stack = Stack<Char>()

        s.forEach { ch ->
            when (ch) {
                '(' -> {
                    stack.push('(')
                }

                '*', '/' -> {
                    while (stack.isNotEmpty()) {
                        if (stack.peek() == '*' || stack.peek() == '/') sb.append(stack.pop())
                        else break
                    }
                    stack.push(ch)
                }

                '+', '-' -> {
                    while (stack.isNotEmpty()) {
                        if (stack.peek() == '(') break
                        sb.append(stack.pop())
                    }
                    stack.push(ch)
                }

                ')' -> {
                    while (stack.isNotEmpty()) {
                        val fromStack = stack.pop()
                        if (fromStack == '(') break
                        sb.append(fromStack)
                    }
                }

                else -> {
                    sb.append(ch)
                }
            }
        }

        while (stack.isNotEmpty()) sb.append(stack.pop())
        println(sb)
    }
}

fun main() {
    전현수_후위_표기식().solution()
}