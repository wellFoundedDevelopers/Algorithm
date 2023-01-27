package jimin.`20week`

/*
<문제>
[양](https://www.acmicpc.net/problem/3184)

<구현 방법>
bfs를 이용하여 bfs로 갈 수 있는 공간에서의 양의 개수와 늑대의 개수를 구해
결과를 구하였다.

<트러블 슈팅>
*/

val visited = mutableListOf<MutableList<Boolean>>()
val ground = mutableListOf<List<String>>()
var r = 0
var c = 0
fun main() {
    readln().split(" ").map { it.toInt() }.apply {
        r = first()
        c = last()
    }
    repeat(r) {
        ground.add(readln().chunked(1))
        visited.add(MutableList(c) { false })
    }
    var sheepNum = 0
    var wolfNum = 0
    for (i in 0 until r) for (j in 0 until c) {
        if (ground[i][j] != "#" && visited[i][j].not()) {
            bfs(i, j).apply {
                if (first) {
                    sheepNum += second
                } else {
                    wolfNum += second
                }
            }
        }
    }
    println("$sheepNum $wolfNum")
}

fun bfs(x: Int, y: Int): Pair<Boolean, Int> {
    val dx = mutableListOf(0, 0, 1, -1)
    val dy = mutableListOf(1, -1, 0, 0)
    val queue = mutableListOf(Point(x, y))
    var sheepNum = 0
    var wolfNum = 0
    if (ground[x][y] == "o") {
        sheepNum += 1
    } else if (ground[x][y] == "v") {
        wolfNum += 1
    }
    visited[x][y] = true

    while (queue.isNotEmpty()) {
        val now = queue.removeAt(0)
        for (i in 0..3) {
            if (now.x + dx[i] in 0 until r && now.y + dy[i] in 0 until c &&
                ground[now.x + dx[i]][now.y + dy[i]] != "#" &&
                visited[now.x + dx[i]][now.y + dy[i]].not()
            ) {
                if (ground[now.x + dx[i]][now.y + dy[i]] == "v") {
                    wolfNum += 1
                } else if (ground[now.x + dx[i]][now.y + dy[i]] == "o") {
                    sheepNum += 1
                }
                queue.add(Point(now.x + dx[i], now.y + dy[i]))
                visited[now.x + dx[i]][now.y + dy[i]] = true
            }
        }
    }
    return if (sheepNum > wolfNum) {
        Pair(true, sheepNum)
    } else {
        Pair(false, wolfNum)
    }
}

data class Point(
    val x: Int,
    val y: Int
)