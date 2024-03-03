package heejik.`52week`

import java.util.*

class `N번째 큰 수` {

    data class Number(
        val x: Int,
        val y: Int,
        val value: Long
    )

    fun solve() {
        val n = readln().toInt()
        val numbers = PriorityQueue<Number>(compareByDescending { it.value })
        val board = MutableList(n) { MutableList(n) { 0L } }

        repeat(n) { x ->
            readln().split(' ').map { it.toLong() }.forEachIndexed { y, l ->
                board[x][y] = l
                if (x == n - 1) {
                    numbers.add(Number(x, y, l))
                }
            }
        }

        repeat(n - 1) {
            val max = numbers.poll()
            numbers.add(Number(max.x - 1, max.y, board[max.x - 1][max.y]))
        }
        println(numbers.peek().value)
    }
}


fun main() {
    `N번째 큰 수`().solve()
}