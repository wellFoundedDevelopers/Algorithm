package jimin.`18week`

/*
<문제>
[트리 순회](https://www.acmicpc.net/problem/1074)

<구현 방법>
n을 2까지 downTo하면서 i-1만큼 제곱을 해준 unit을 기준으로 4칸 중 어디에 위치하는지를 본다.
그것을 2가 될 때까지 r과 c를 unit으로 나눈 나머지로 갱신하면서 확인해 위치에 따라 checkPosition 함수를 통해
result를 계산한다.

<트러블 슈팅>
*/

import kotlin.math.pow

fun main() {
    var (n, r, c) = readln().split(" ").map { it.toInt() }
    var result = 0
    for (i in n downTo 2) {
        val unit = 2.0.pow(i - 1).toInt()
        result += checkPosition(r / unit, c / unit) * unit * unit
        r %= unit
        c %= unit
    }
    result += checkPosition(r, c)
    println(result)
}

fun checkPosition(r: Int, c: Int): Int {
    if (r % 2 == 0 && c % 2 == 0) {
        return 0
    } else if (r % 2 == 0 && c % 2 == 1) {
        return 1
    } else if (r % 2 == 1 && c % 2 == 0) {
        return 2
    } else {
        return 3
    }
}