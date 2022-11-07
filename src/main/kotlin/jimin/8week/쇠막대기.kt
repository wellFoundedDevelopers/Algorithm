package jimin.`8week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[쇠막대기] https://www.acmicpc.net/problem/1283

<구현 방법>
일단 열림 '('은 stack에 넣고
닫힘이 레이저일 경우 현재 스택에 쌓여있는걸 다 자르는 거니까 stack의 사이즈 만큼 result에 더해줬다.
닫힘이 막대기일 경우 현재 막대기만 끝나는 것이기 때문에 result에 + 1 해주었다.

<트러블 슈팅>
처음에 stack을 int로 하여 레이저이면 stack의 요소에 모두 + 1 을 해주고
막대기가 끝나면 스택의 last에 1을 더한 만큼을 result에 더해 풀었다.
이렇게 하니 시간초과가 나서 구글링했다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val laser = readLine()
    var stack = mutableListOf<Char>()
    var result = 0
    laser.forEachIndexed { index, c ->
        if (c == '(') {
            stack.add(c)
        } else if (c == ')') {
            if (laser[index - 1] == '(') {
                stack.removeLast()
                result += stack.size
            } else {
                stack.removeLast()
                result += 1
            }
        }
    }
    println(result)
}