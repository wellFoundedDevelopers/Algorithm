package jimin.`23week`

/*
<문제>
[합분해](https://www.acmicpc.net/problem/2225)

<구현 방법>
n은 n - m에 m을 더한 것이다.
이 m은 0 ~ n까지 이다.
DP[K][N] = DP[K-1][0] + DP[K-1][1] + ... + DP[K-1][N]

<트러블 슈팅>
오늘도 구글링..^^ https://hongjw1938.tistory.com/63
*/

class 합분해 {
    fun solve() {
        val (n, k) = readln().split(" ").map{ it.toInt() }
        val dp = MutableList(k + 1){ MutableList(n + 1) { 0 } }
        dp[1] = MutableList(n + 1) { 1 }

        for(i in 2 .. k) {
            for(j in 0 .. n) {
                for (k in 0 .. j) {
                    dp[i][j] += dp[i - 1][k]
                    dp[i][j] %= 1_000_000_000
                }
            }
        }
        println(dp.last().last())
    }
}

fun main() {
    합분해().solve()
}