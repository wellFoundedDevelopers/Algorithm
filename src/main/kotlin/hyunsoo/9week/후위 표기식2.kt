package hyunsoo.`9week`

import java.util.*

/**
 * <문제>
 * [후위 표기식2](https://www.acmicpc.net/problem/1935)
 *
 */

val alphabetList = ('A'..'N').toList()
val stack = Stack<Double>()

fun main() {

    val numCnt = readln().toInt()

    val expression = readln().map { it.toString() }.toMutableList()

    println(expression)
    repeat(numCnt) { index ->
        val num = readln()
        expression.replaceAll { string ->
            if (string == "${alphabetList[index]}") num
            else string
        }
    }
    println(expression)

    expression.forEach { string ->

        string.toIntOrNull()?.let { num ->
            stack.add(num.toDouble())
        } ?: calculate(string)

    }

    String
        .format("%.2f", stack.pop())
        .run {
            println(this)
        }

}

fun calculate(operator: String) {

    val secondNum = if (stack.isNotEmpty()) stack.pop() else 0.0
    val firstNum = if (stack.isNotEmpty()) stack.pop() else 0.0

    when (operator) {
        "+" -> stack.add(firstNum + secondNum)

        "-" -> stack.add(firstNum - secondNum)

        "*" -> stack.add(firstNum * secondNum)

        "/" -> stack.add(firstNum / secondNum)
    }
}