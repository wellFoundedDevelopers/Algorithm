package jimin.`19week`

/*
<문제>
[날짜 계산](https://www.acmicpc.net/problem/1476)

<구현 방법>
e, s, m을 1씩 빼주어 0~14, 0~27, 0~18로 나머지가 나오도록 하였다.
그런 후 num에 1을 더해줬다.

<트러블 슈팅>
질문방보고 힌트를 얻었다.
*/

fun main() {
    var (e, s, m) = readln().split(" ").map{ it.toInt() }
    e -= 1
    s -= 1
    m -= 1
    var tmp = 0
    while (true) {
        val num = tmp * 15 + e
        if (num % 28 == s && num % 19 == m) {
            println(num + 1)
            break
        }
        tmp += 1
    }
}