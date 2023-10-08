package heejik.`47week`

import kotlin.math.min

class 회장뽑기 {

    fun solve() {
        val n = readln().toInt()
        val board = MutableList(n + 1) { MutableList(n + 1) { Int.MAX_VALUE } }
        repeat(n + 1) {
            board[it][it] = 0
        }

        while (true) {
            val (a, b) = readln().split(' ').map { it.toInt() }
            if (a == -1) break
            board[a][b] = 1
            board[b][a] = 1
        }


        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (board[i][k] == Int.MAX_VALUE || board[k][j] == Int.MAX_VALUE) continue
                    board[i][j] = min(board[i][j], board[i][k] + board[k][j])
                }
            }
        }

        val minScore = board.drop(1).minOf { it.filter { it != Int.MAX_VALUE }.max() }
        val candidates = mutableListOf<Int>()

        board.forEachIndexed { index, ints ->
            if (ints.filter { it != Int.MAX_VALUE }.max() == minScore)
                candidates.add(index)
        }
        println("$minScore ${candidates.count()}")
        println(candidates.joinToString(" "))
    }
}


fun main() {
    회장뽑기().solve()
}