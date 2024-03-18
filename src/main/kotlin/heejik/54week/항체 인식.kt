package heejik.`54week`

class `항체 인식` {
    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    fun solve() {
        val originBoard = mutableListOf<List<Int>>()
        val afterBoard = mutableListOf<List<Int>>()
        val differencePoses = mutableListOf<Pos>()
        val (n, m) = readln().split(' ').map { it.toInt() }

        repeat(n) {
            val row = readln().split(' ').map { it.toInt() }
            originBoard.add(row)
        }

        repeat(n) {
            val row = readln().split(' ').map { it.toInt() }
            afterBoard.add(row)
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (originBoard[i][j] != afterBoard[i][j]) {
                    differencePoses.add(Pos(i, j))
                }
            }
        }

        val deque = ArrayDeque<Pos>()
        val visited = mutableListOf<Pos>()
        if (differencePoses.isNotEmpty()) {
            deque.add(differencePoses.first())
            visited.add(differencePoses.first())
        }

        while (deque.isNotEmpty()) {
            val now = deque.removeFirst()

            for (i in 0 until 4) {
                val nx = now.x + dx[i]
                val ny = now.y + dy[i]
                val newPos = Pos(nx, ny)
                if (nx in 0 until n && ny in 0 until m && newPos !in visited) {
                    if (originBoard[nx][ny] == originBoard[now.x][now.y]) {
                        deque.add(newPos)
                        visited.add(newPos)
                    }
                }
            }
        }


        if (visited.size > 1) {
            val standard = afterBoard[visited.first().x][visited.first().y]
            visited.forEach {
                if (standard != afterBoard[it.x][it.y]) {
                    println("NO")
                    return
                }
            }
        }

        val answer: () -> Unit =
            if (visited.size == differencePoses.size) {
                { println("YES") }
            } else {
                { println("NO") }
            }

        answer()
    }
}


fun main() {
    `항체 인식`().solve()
}