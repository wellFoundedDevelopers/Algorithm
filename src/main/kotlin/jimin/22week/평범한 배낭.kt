package jimin.`22week`

/*
<문제>
[평범한 배낭](https://www.acmicpc.net/problem/12865)

<구현 방법>
1. 현재 배낭의 허용 무게보다 넣을 물건의 무게가 더 크다면 넣지 x
2. 물건을 넣을 수 있다면 다음 두개의 가치 중 더 큰 것을 선택해서 넣는다.
    2-1. 현재 넣을 물건의 무게만큼 배낭에서 뺀다. 그리고 현재 물건을 넣는다.
    2-2. 현재 물건을 넣지 않고 이전 배낭 그대로 가지고 간다.

<트러블 슈팅>
dp는 항상 모르겠다...
https://hongcoding.tistory.com/50 참고하였다
*/


import java.lang.Integer.max

class `평범한 배낭` {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val products = mutableListOf(Pair(0, 0))
        repeat(n) {
            products.add(readln().split(" ").map { it.toInt() }.run {
                Pair(first(), last())
            })
        }

        val values = MutableList(n + 1) { MutableList(k + 1) { 0 } }
        for (i in 1..n) {
            for (j in 1..k) {
                if (j < products[i].first) {
                    values[i][j] = values[i - 1][j]
                } else {
                    values[i][j] = max(values[i - 1][j], values[i - 1][j - products[i].first] + products[i].second)
                }
            }
        }

        println(values[n][k])
    }
}


fun main() {
    `평범한 배낭`().solve()
}