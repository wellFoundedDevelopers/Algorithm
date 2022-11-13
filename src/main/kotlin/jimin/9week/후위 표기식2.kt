package jimin.`9week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.DecimalFormat
import java.util.Stack

/*
<문제>
[후위 표기식2](https://www.acmicpc.net/problem/1935)

<구현 방법>
formulaInfo에 문자에 해당하는 숫자값을 넣고,
이를 후위계산을 진행하면서 꺼내썼다.

<트러블 슈팅>
Float을 Double로 하니까 해결 되었다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toInt()
    val formula = readLine()
    val formulaInfo = mutableMapOf<Char, Double>()
    repeat(n){
        formulaInfo['A' + it] = readLine().toDouble()
    }
    val stack = Stack<Double>()
    formula.forEach {
        if (it in 'A'..'Z'){
            stack.push(formulaInfo[it])
        } else {
            val second = stack.pop()
            val first = stack.pop()
            when (it) {
                '*' -> {
                    stack.push(first * second)
                }
                '/' -> {
                    stack.push(first / second)
                }
                '+' -> {
                    stack.push(first + second)
                }
                '-' -> {
                    stack.push(first - second)
                }
            }
        }
    }
    println(DecimalFormat(".00").format(stack.pop()))

}