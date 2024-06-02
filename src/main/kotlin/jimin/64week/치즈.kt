package jimin.`64week`


class 치즈 {
    private val IN = 0
    private val CHEESE = 1
    private val OUT = 2

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val ground = mutableListOf<MutableList<Int>>()
        for (i in 0 until n) {
            ground.add(readln().split(" ").map { it.toInt() } as MutableList<Int>)
        }

        var time = -1
        var isOver = false
        while (!isOver) {
            val visited = MutableList(n) { MutableList(m) { false } }
            isOver = true
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (!visited[i][j]) {
                        if (i == 0 && j == 0) {
                            ground[i][j] = OUT
                            bfs(i, j, n, m, ground, visited, OUT)
                        } else if (ground[i][j] == CHEESE) {
                            isOver = false
                            bfs(i, j, n, m, ground, visited, CHEESE)
                        }
                    }
                }
            }

            time += 1
        }

        println(time)

    }

    fun bfs(x: Int, y: Int, n: Int, m: Int, ground: MutableList<MutableList<Int>>, visited: MutableList<MutableList<Boolean>>, type: Int) {
        val dx = mutableListOf(0, 0, 1, -1)
        val dy = mutableListOf(1, -1, 0, 0)
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(x to y)
        val outs = mutableListOf<Pair<Int, Int>>()

        while (queue.isNotEmpty()) {
            val (nx, ny) = queue.removeFirst()

            for (i in 0 until 4) {
                if (nx + dx[i] in 0 until n && ny + dy[i] in 0 until m && !visited[nx + dx[i]][ny + dy[i]]) {
                    if (type == OUT){
                        if(ground[nx + dx[i]][ny + dy[i]] != CHEESE) {
                            queue.addLast(nx + dx[i] to ny + dy[i])
                            visited[nx + dx[i]][ny + dy[i]] = true
                            ground[nx + dx[i]][ny + dy[i]] = type
                        }
                    }else if (type == CHEESE) {
                        if(ground[nx + dx[i]][ny + dy[i]] == CHEESE) {
                            queue.addLast(nx + dx[i] to ny + dy[i])
                            visited[nx + dx[i]][ny + dy[i]] = true
                        }
                    }
                }
            }

            if (type == CHEESE) {
                var out = 0
                for (i in 0 until 4) {
                    if (nx + dx[i] in 0 until n && ny + dy[i] in 0 until m && ground[nx + dx[i]][ny + dy[i]] == OUT) {
                        out += 1
                    }
                }
                if (out >= 2){
                    outs.add(nx to ny)
                }
            }
        }

        outs.forEach {
            ground[it.first][it.second] = OUT
        }

    }
}

fun main() {
    치즈().solve()
}