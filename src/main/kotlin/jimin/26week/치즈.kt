package jimin.`26week`

/*
<문제>
[치즈](https://www.acmicpc.net/problem/2636)

<구현 방법>
우선 bfs를 돌아서 공기인 것을 체크한다.
이 공기에 닿아있는 곳이 다음 시간에 녹을 곳이다.
전체를 돌면서 치즈이면서 공기에 닿아있는 곳의 좌표를 outsideCheese에 담는다.
이 outsideCheese 좌표들을 공기로 바꿔주고, 다시 bfs를 돌면서 공기를 업데이트해준다.
그러면서 전체에 치즈가 남아있지 않는다면 outsideCheese의 사이즈를 num에 저장하고 while문을 탈출한다.

<트러블 슈팅>
*/


class 치즈 {
    val cheese = mutableListOf<MutableList<Int>>()
    val dx = mutableListOf(1, -1, 0, 0)
    val dy = mutableListOf(0, 0, -1, 1)
    fun solve() {
        val (n, m) = readln().split(" ").map{ it.toInt() }
        var time = 0
        var num = 0
        repeat(n) {
            cheese.add(readln().split(" ").map{ it.toInt() } as MutableList)
        }

        bfs(n, m)

        while(true) {
            val outsideCheese = mutableListOf<Pair<Int, Int>>()
            for(i in 0 until n) {
                for(j in 0 until m) {
                    if (cheese[i][j] == CHEESE) {
                        var outside = false
                        for (k in 0 until 4) {
                            if (cheese[i + dx[k]][j + dy[k]] == AIR) {
                                outside = true
                                break
                            }
                        }
                        if (outside) {
                            outsideCheese.add(Pair(i, j))
                        }
                    }
                }
            }

            outsideCheese.forEach{ c ->
                cheese[c.first][c.second] = AIR
            }
            bfs(n, m)
            time += 1

            if (cheese.all{ it.none{ it == CHEESE}}) {
                num = outsideCheese.size
                break
            }

        }
        println(time)
        println(num)
    }

    fun bfs(n: Int, m: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(0, 0))
        val visited = MutableList(n){ MutableList(m){ false } }

        while(queue.isNotEmpty()) {
            val now = queue.removeFirst()
            for(i in 0 until 4){
                if (now.first + dx[i] in 0 until n && now.second + dy[i] in 0 until m &&
                        cheese[now.first + dx[i]][now.second + dy[i]] != CHEESE &&
                        visited[now.first + dx[i]][now.second + dy[i]].not()) {
                    cheese[now.first + dx[i]][now.second + dy[i]] = AIR
                    queue.add(Pair(now.first + dx[i], now.second + dy[i]))
                    visited[now.first + dx[i]][now.second + dy[i]] = true
                }
            }
        }
    }


    companion object{
        const val AIR = 2
        const val NOT_CHEESE = 0
        const val CHEESE = 1
    }
}

fun main() {
    치즈().solve()
}