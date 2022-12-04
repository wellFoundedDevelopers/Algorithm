package jimin.`12week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/*
<문제>
[안녕](https://www.acmicpc.net/problem/1535)

<구현 방법>
dp이용


<트러블 슈팅>
배낭문제 참고
https://gsmesie692.tistory.com/113
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val dp = MutableList(n + 1) { MutableList(100) { 0 } }
    val strength = readLine().split(" ").map { it.toInt() }.toMutableList()
    val joy = readLine().split(" ").map { it.toInt() }.toMutableList()

    for (i in 1..n) {
        for (j in 1..99) {
            dp[i][j] = dp[i-1][j]
            if (strength[i - 1] <= j) {
                dp[i][j] = max(joy[i - 1] + dp[i - 1][j - strength[i - 1]], dp[i][j])
            }
        }
    }

    println(dp[n][99])

}
