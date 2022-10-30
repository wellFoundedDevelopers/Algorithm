package jimin.`7week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[한 줄로 서기] https://www.acmicpc.net/problem/1138

<구현 방법>
일단 개수만큼 0으로 채워진 list를 만든다.
그리고 앞에서부터 리스트의 0의 개수를 세어 이게 기록된 결과와 같은 위치면 해당 index + 1을 넣어준다.

<트러블 슈팅>
index가 0인 경우를 따로 빼놓지 않아도 되는데,
따로 빼놓아서 바로 넣어주면 시간이 줄어든다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val recordList = readLine().split(" ").map { it.toInt() }
    val result = MutableList(n) { 0 }

    recordList.forEachIndexed { index, info ->
        var num = 0
        run breaker@{
            repeat(n) {
                if (result[it] == 0) {
                    if (num == info) {
                        result[it] = index + 1
                        return@breaker
                    }
                    num += 1
                }
            }
        }
    }
    println(result.joinToString(" "))
}
