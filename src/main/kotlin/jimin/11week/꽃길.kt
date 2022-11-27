package jimin.`11week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min

/*
<문제>
[꽃길](https://www.acmicpc.net/problem/14620)

<구현 방법>
dfs로 돌면서 num이 3이면 백트랙킹한다.

<트러블 슈팅>
처음엔 dfs로 안풀려고 했다가 실패해서 dfs로 돌아갔다.

 */

var min = Int.MAX_VALUE
var n: Int = 0
val ground = mutableListOf<MutableList<Int>>()
val visited = mutableListOf<MutableList<Boolean>>()
val direction = listOf(
    Pair(-1, 0),
    Pair(0, 0),
    Pair(1, 0),
    Pair(0, -1),
    Pair(0, 1)
)

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    repeat(n) {
        ground.add(readLine().split(" ").map { it.toInt() }.toMutableList())
        visited.add(MutableList(n) { false })
    }

    dfs(0, 0)
    println(min)
}

fun dfs(num: Int, sum: Int) {
    if (num == 3) {
        min = min(min, sum)
        return@dfs
    }

    for (i in 1 until n - 1) {
        for (j in 1 until n - 1) {
            val checked = checkGround(i, j)
            if (checked.first) {
                visitGround(i, j, true)
                dfs(num + 1, sum + checked.second)
                visitGround(i, j, false)
            }
        }
    }
}

fun checkGround(x: Int, y: Int): Pair<Boolean, Int> {
    var price = 0
    direction.forEach {
        if (visited[x + it.first][y + it.second]) {
            return Pair(false, 0)
        }
        price += ground[x + it.first][y + it.second]
    }
    return Pair(true, price)
}

fun visitGround(x: Int, y:Int, boolean: Boolean){
    direction.forEach{
        visited[x + it.first][y + it.second] = boolean
    }
}