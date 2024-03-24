package heejik.`55week`

class `보이저 1호` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    enum class Direction(val move: Pos) {
        U(Pos(-1, 0)), R(Pos(0, 1)), D(Pos(1, 0)), L(Pos(0, -1))
    }

    fun solve() {
        val (n, m) = readln().split(' ').map { it.toInt() }
        val board = mutableListOf<String>()
        var maxTimes = 0
        var answerDirection: Direction = Direction.U

        repeat(n) {
            board.add(readln())
        }

        val (originX, originY) = readln().split(' ').map { it.toInt() - 1 }

        for (dir in Direction.values()) {
            var (nx, ny) = originX to originY
            var nowDir = dir
            var time = 0
            while (time <= 500*500*2) {
                time++
                nx += nowDir.move.x
                ny += nowDir.move.y
                if (nx !in 0 until n || ny !in 0 until m) break
                if (board[nx][ny] == 'C') break
                if (board[nx][ny] == '.') continue
                nowDir = when (nowDir) {
                    Direction.U -> if (board[nx][ny] == '/') Direction.R else Direction.L
                    Direction.R -> if (board[nx][ny] == '/') Direction.U else Direction.D
                    Direction.D -> if (board[nx][ny] == '/') Direction.L else Direction.R
                    Direction.L -> if (board[nx][ny] == '/') Direction.D else Direction.U
                }
            }

            if (time > maxTimes) {
                maxTimes = time
                answerDirection = dir
            }
        }

        println(answerDirection.name)
        with(maxTimes) {
            if (this > 500*500*2) println("Voyager")
            else println(this)
        }
    }
}


fun main() {
    `보이저 1호`().solve()
}