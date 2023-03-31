package jimin.`29week`

/*
<문제>
[안녕](https://www.acmicpc.net/problem/1535)

<구현 방법>
세로를 n, 가로를 100으로 하는 dp 테이블을 만들어주었다.
인사시 체력과 기쁨을 Pair로 묶어 hiInfo 리스트로 만들어준 다음에 first, second 순으로 오름차순 정렬해주었다.
현재 나의 체력만큼 빼면서 전 단계인 것의 기쁨 + 현재 단계 기쁨이 큰지, 그냥 전 단계 기쁨이 큰지를 비교해서 dp 테이블을 만들어주었다.

<트러블 슈팅>
처음으로 dp 구글링 안하고 풀었다... 후후..
*/

import java.lang.Integer.max

class 안녕 {
    fun solve() {
        val n = readln().toInt()
        val dp = MutableList(n) { MutableList(100) { 0 } }
        val healths = readln().split(" ").map { it.toInt() }
        val joys = readln().split(" ").map { it.toInt() }
        val hiInfo = mutableListOf<Pair<Int, Int>>()
        repeat(n) {
            hiInfo.add(Pair(healths[it], joys[it]))
        }
        hiInfo.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

        for (i in 0 until n) {
            for (j in 0 until 100) {
                if (i == 0) {
                    if (j >= hiInfo[i].first) {
                        dp[i][j] = hiInfo[i].second
                    }
                } else {
                    if (j >= hiInfo[i].first) {
                        dp[i][j] = max(dp[i - 1][j - hiInfo[i].first] + hiInfo[i].second, dp[i - 1][j])
                    } else {
                        dp[i][j] = dp[i - 1][j]
                    }
                }
            }
        }

        println(dp.last().last())
    }
}

fun main() {
    `안녕`().solve()
}