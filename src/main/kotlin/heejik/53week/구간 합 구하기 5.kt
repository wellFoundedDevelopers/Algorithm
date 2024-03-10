package heejik.`53week`

class `구간 합 구하기 5` {

    private var n: Int = 0
    private var m: Int = 0
    private val board = mutableListOf<MutableList<Int>>()
    private val poses = mutableListOf<List<Int>>()
    fun solve() {
        input()
        changeBoard()
        poses.forEach {
            getPrefixSum(it[0] - 1, it[1] - 1, it[2] - 1, it[3] - 1).also { answer ->
                println(answer)
            }
        }
    }

    private fun input() {
        readln().split(' ').map { it.toInt() }.also {
            n = it.first()
            m = it.last()
        }
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
        repeat(m) {
            poses.add(readln().split(' ').map { it.toInt() })
        }
    }

    private fun changeBoard() {
        for (x in 0 until n) {
            for (y in 0 until n) {
                val minusX = if (x == 0) -1 else x - 1
                val minusY = if (y == 0) -1 else y - 1
                board[x][y] += (if (minusY != -1) board[x][minusY] else 0) +
                        (if (minusX != -1) board[minusX][y] else 0) -
                        (if (minusX != -1 && minusY != -1) board[minusX][minusY] else 0)
            }
        }
    }

    private fun getPrefixSum(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        val leftSum = if (y1 == 0) 0 else board[x2][y1 - 1]
        val upSum = if (x1 == 0) 0 else board[x1 - 1][y2]
        val diagonalSum = if (x1 == 0 || y1 == 0) 0 else board[x1 - 1][y1 - 1]
        return board[x2][y2] - leftSum - upSum + diagonalSum
    }
}

fun main() {
    `구간 합 구하기 5`().solve()
}