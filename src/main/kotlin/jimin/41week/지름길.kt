package jimin.`41week`

/*
<문제>
[지름길](https://www.acmicpc.net/problem/1446)

<구현 방법>
1차원 dp 이용.

<트러블 슈팅>
*/

data class Road(
        val start: Int,
        val end: Int,
        val length: Int
)

class 지름길 {
    fun solve() {
        val (n, d) = readln().split(" ").map { it.toInt() }
        val roadInfo = mutableListOf<Road>()
        val dp = MutableList(d + 1) { it }
        repeat(n) {
            readln().split(" ").map { it.toInt() }.apply {
                roadInfo.add(Road(this[0], this[1], this[2]))
            }
        }
        roadInfo.sortWith(compareBy({ it.start }, { it.end }, { it.length }))

        for (i in 0 until n) {
            val (start, end, length) = roadInfo[i]
            for (j in 0 .. d) {
                if (j >= end) {
                    if (dp[j] > dp[start] + length + j - end) {
                        dp[j] = dp[start] + length + j - end
                    }
                }
            }
        }

        println(dp.last())
    }
}

fun main() {
    지름길().solve()
}