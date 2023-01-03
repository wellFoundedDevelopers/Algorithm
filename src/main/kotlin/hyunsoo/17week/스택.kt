package hyunsoo.`17week`

import java.util.Stack

/**
 *
 * <문제>
 * [스택](https://www.acmicpc.net/problem/10828)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */

fun main() {

    val stack = Stack<Int>()
    val cnt = readln().toInt()

    repeat(cnt) {

        when (val command = readln()) {
            "top" -> {
                println(if (stack.isEmpty()) -1 else stack.peek())
            }

            "size" -> {
                println(stack.size)
            }

            "empty" -> {
                println(if (stack.isEmpty()) 1 else 0)
            }

            "pop" -> {
                println(if (stack.isEmpty()) -1 else stack.pop())
            }

            else -> {
                val (_, target) = command.split(" ")
                stack.push(target.toInt())
            }

        }
    }


}