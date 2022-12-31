package jimin.`13week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[수 이어 쓰기 1](https://www.acmicpc.net/problem/1748)

<구현 방법>
일의 자리 숫자일 때는 n이나 9를 넣는다.
일의 자리 이상일 때는 99, 999, 9999 일때의 경우를 더해주면서
마지막 q-1일때도 계산해 더해준다.

<트러블 슈팅>
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val q = n.toString().length - 1
    var sum = if (n < 10) n else 9
    var ten = 1
    repeat(q) {
        ten *= 10
        if (it == q - 1) {
            sum += (n - (ten - 1)) * (it + 2)
        } else {
            sum += (ten * 9) * (it + 2)
        }
    }
    println(sum)
}
