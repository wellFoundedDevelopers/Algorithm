package jimin.`31week`

/*
<문제>
[운동](https://www.acmicpc.net/problem/1956)

<구현 방법>
플루이드 워셜 알고리즘을 이용!
3중 for문을 써서 경로가 짧은 것을 업데이트해주었다.
그런후 roadInfo[i][j]와 roadInfo[j][i]를 더해서 사이클을 만들었다.

<트러블 슈팅>
플루이드 워셜 몰라서 이코테 봄
*/

import java.lang.Integer.*

class 운동 {

    fun solve() {
        val (e, v) = readln().split(" ").map{ it.toInt() }
        val roadInfo = MutableList(e){ MutableList(e){ INF } }
        repeat(v) {
            val (a, b, c) = readln().split(" ").map{ it.toInt() }
            roadInfo[a - 1][b - 1] = c
        }

        for(i in 0 until e) {
            for (j in 0 until e){
                for (k in 0 until e) {
                    if (roadInfo[j][i] != INF && roadInfo[i][k] != INF) {
                        roadInfo[j][k] = min(roadInfo[j][k], roadInfo[j][i] + roadInfo[i][k])
                    }
                }
            }
        }

        var mini = INF

        for (i in 0 until e) {
            for (j in 0 until e) {
                if (roadInfo[i][j] != INF && roadInfo[j][i] != INF) {
                    mini = min(mini, roadInfo[i][j] + roadInfo[j][i])
                }
            }
        }
        if (mini == INF) {
            println(-1)
        } else {
            println(mini)
        }
    }

    companion object{
        const val INF = Int.MAX_VALUE
    }
}

fun main() {
    운동().solve()
}