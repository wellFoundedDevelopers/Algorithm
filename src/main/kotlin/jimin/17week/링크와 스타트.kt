package jimin.`17week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min

/*
<문제>
[링크와 스타트](https://www.acmicpc.net/problem/15661)

<구현 방법>
getCombination으로 조합을 먼저 구한다.
이때 1개일때는 값이 0 인데 경험치는 1이상이기 때문에 안됨.
그리고 조합이니 n/2개만 구해도 되기 때문에 2이상 n/2 이하로 조합을 구함
조합을 구하고 나면 이중for문을 돌면서 team의 경험치를 계산함

<트러블 슈팅>
이중 for문을 돌 때 j의 시작을 0으로 하니 2번씩 더 더해졌음
따라서 i부터 시작으로 바꿈
아래 구글링 참고
https://velog.io/@gandi0330/Python-%EB%B0%B1%EC%A4%80-%EB%A7%81%ED%81%AC%EC%99%80-%EC%8A%A4%ED%83%80%ED%8A%B8-15661%EB%B2%88-%EB%B8%8C%EB%A3%A8%ED%8A%B8%ED%8F%AC%EC%8A%A4
*/

var n: Int = 0
val people = mutableListOf<List<Int>>()
var result = Int.MAX_VALUE
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    repeat(n) {
        people.add(readLine().split(" ").map { it.toInt() })
    }

    for (i in 2..n / 2) {
        getCombination(i, 0, MutableList(n) { false })
    }

    println(result)
}

fun getCombination(size: Int, idx: Int, visited: MutableList<Boolean>) {
    if (visited.filter { it }.size == size) {
        getLevel(visited)
        return
    }

    for (i in idx until n) {
        visited[i] = true
        getCombination(size, i + 1, visited)
        visited[i] = false
    }
}

fun getLevel(visited: MutableList<Boolean>) {
    var teamA = 0
    var teamB = 0
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (visited[i] && visited[j]) {
                teamA += people[i][j] + people[j][i]
            }
            if (visited[i].not() && visited[j].not()) {
                teamB += people[i][j] + people[j][i]
            }
        }
    }
    result = min(abs(teamA - teamB), result)
}