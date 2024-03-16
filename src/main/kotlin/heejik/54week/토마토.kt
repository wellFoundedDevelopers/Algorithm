package heejik.`54week`

class 토마토 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    fun solve() {
        val (n, m) = readln().split(' ').map { it.toInt() }
        val board = List(size = m) { IntArray(size = n) }
        val deque = ArrayDeque<Pos>()
        var ripeCount = 0
        var canCount = n*m
        var day = 0

        repeat(m) { i ->
            val row = readln().split(' ').map { it.toInt() }.toMutableList()
            repeat(n) { j ->
                board[i][j] = row[j]
                if (row[j] == 1) {
                    deque.add(Pos(i, j))
                    ripeCount++
                }
                if (row[j] == -1) canCount --
            }
        }

        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, 1, -1)
        val nextDeque = ArrayDeque<Pos>()
        var tmpRipeCount = deque.size
        var nextRipeCount = 0
        var isRipen = false

        while (deque.isNotEmpty()) {
            repeat(tmpRipeCount) {
                val (x, y) = deque.removeFirst()
                for (i in 0..3) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx in 0 until m && ny in 0 until n && board[nx][ny] == 0) {
                        nextDeque.add(Pos(nx, ny))
                        board[nx][ny] = 1
                        ripeCount++
                        nextRipeCount++
                        isRipen = true
                    }
                }
            }
            tmpRipeCount = nextRipeCount
            nextRipeCount = 0
            deque.addAll(nextDeque)
            nextDeque.clear()
            if (isRipen) day++
            isRipen = false
        }

        if (ripeCount == canCount) println(day)
        else println(-1)
    }
}


fun main() {
    토마토().solve()
}