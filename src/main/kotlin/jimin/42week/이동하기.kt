package jimin.`42week`

/*
<문제>
[이동하기](https://www.acmicpc.net/problem/11048)

<구현 방법>
dp를 이용하였다.
이동을 오른쪽, 아래, 오른쪽 대각선 아래 밖에 하지 못하는데
이를 반대로 생각하면 왼쪽, 위, 왼쪽 대각선 위 에서의 값을 가져오면 된다. (dp!)
따라서 왼쪽, 위, 왼쪽 대각선 위 중 제일 큰 값인 maxi 값을 찾아서 dp 테이블을 업데이트하는 식으로 해결했다.

<트러블 슈팅>
처음에 dfs로 풀었는데 시간초과 나서, 문제유형을 보니 dp길래 dp로 해결했다.
문제만 보고 바로 dp로 풀어야 하는지는 모르겠다.
 */

import java.lang.Integer.max

class 이동하기 {
    val dx = mutableListOf(-1, 0, -1)
    val dy = mutableListOf(0, -1, -1)
    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val maze = mutableListOf<MutableList<Int>>()
        val dp = mutableListOf<MutableList<Int>>()
        repeat(n) {
            maze.add(readln().split(" ").map { it.toInt() } as MutableList<Int>)
            dp.add(MutableList(m) { 0 })
        }

        repeat(n) { i ->
            repeat(m) { j ->
                var maxi = 0
                repeat(3) { k ->
                    if (i + dx[k] in 0 until n && j + dy[k] in 0 until m) {
                        maxi = max(dp[i + dx[k]][j + dy[k]], maxi)
                    }
                }
                dp[i][j] = maxi + maze[i][j]
            }
        }

        println(dp[n - 1][m - 1])

    }
}

fun main() {
    이동하기().solve()
}