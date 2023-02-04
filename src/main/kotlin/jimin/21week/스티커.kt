package jimin.`21week`

/*
<문제>
[스티커](https://www.acmicpc.net/problem/9465)

<구현 방법>
(0,0)을 기준으로 (1,1)이나 (1,2)로 이동한다. 한 칸 아래나 두 칸 아래로!
이걸 생각해서 현재 위치로 부터 한 칸 아래 전 과 두 칸 아래를 비교해서
큰 것을 현재 위치에 넣어주면 된다!

<트러블 슈팅>
https://m.blog.naver.com/occidere/220786307316
해당 블로그의 문제 설명을 참고하였다!!
*/

import java.lang.Integer.max

class 스티커 {
    fun solve() {
        repeat(readln().toInt()) {
            val n = readln().toInt()
            val stickers = mutableListOf<List<Int>>()
            repeat(2) {
                stickers.add(readln().split(" ").map { it.toInt() })
            }
            val dp = MutableList(2) { MutableList(n + 1) { 0 } }
            dp[0][1] = stickers[0][0]
            dp[1][1] = stickers[1][0]
            for (i in 2..n) {
                dp[0][i] = max(dp[1][i - 1] + stickers[0][i - 1], dp[1][i - 2] + stickers[0][i - 1])
                dp[1][i] = max(dp[0][i - 1] + stickers[1][i - 1], dp[0][i - 2] + stickers[1][i - 1])
            }
            println(max(dp[0][n], dp[1][n]))
        }
    }
}

fun main() {
    스티커().solve()
}