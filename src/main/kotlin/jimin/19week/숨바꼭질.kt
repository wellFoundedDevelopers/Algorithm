package jimin.`19week`

/*
<문제>
[숨박꼭질](https://www.acmicpc.net/problem/1697)

<구현 방법>
bfs를 이용하였다. 이때 방문한 곳을 방문하면 시간 초과가 뜨기 때문에
visited를 이용해서 방문처리를 해주었다.

<트러블 슈팅>
visited 방문처리 안하고 했더니 시간초과 떴다..
구글링해서 visited 사용해야한다는 것 앎음.
그리고 문제보고 바로 bfs라고 안떠올랐다.
*/

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val visited = MutableList(100001) { false }
    if (n < k) {
        val queue = mutableListOf(Pair(n, 0))
        while (queue.isNotEmpty()) {
            val num = queue.removeAt(0)
            if (num.first * 2 == k) {
                println(num.second + 1)
                break
            } else if (num.first * 2 in 0 .. 100000 && visited[num.first * 2].not()) {
                queue.add(num.copy(first = num.first * 2, second = num.second + 1))
                visited[num.first * 2] = true
            }
            if (num.first - 1 == k) {
                println(num.second + 1)
                break
            } else if (num.first - 1 in 0 .. 100000 && visited[num.first - 1].not()) {
                queue.add(num.copy(first = num.first - 1, second = num.second + 1))
                visited[num.first - 1] = true
            }
            if (num.first + 1 == k) {
                println(num.second + 1)
                break
            } else if (num.first + 1 in 0 .. 100000 && visited[num.first + 1].not()) {
                queue.add(num.copy(first = num.first + 1, second = num.second + 1))
                visited[num.first + 1] = true
            }
        }
    } else {
        println(n - k)
    }
}

