package heejik.`2week`

import java.lang.StringBuilder
import java.util.*

fun main() {
    val s = readln()
    val answer = StringBuilder()
    val stack = Stack<Char>()
    var isTag = false

    s.forEach {
        when (it) {
            '<' -> {
                answer.appendAndClear(stack, it)
                isTag = true
            }

            '>' -> {
                answer.appendAndClear(stack, it)
                isTag = false
            }

            ' ' -> {
                if (isTag) {
                    answer.append(it)
                } else {
                    answer.appendAndClear(stack, it)
                }
            }

            else -> {
                if (isTag){
                    answer.append(it)
                } else {
                    stack.add(it)
                }
            }
        }
    }
    answer.append(stack.toStringElements().reversed())
    println(answer.toString())
}

fun Stack<Char>.toStringElements() = this.elements().toList().joinToString("")

fun StringBuilder.appendAndClear(stack: Stack<Char>, char: Char){
    this.append(stack.toStringElements().reversed())
    stack.clear()
    this.append(char)
}
