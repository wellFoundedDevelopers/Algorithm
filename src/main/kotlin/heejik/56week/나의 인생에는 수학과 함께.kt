package heejik.`56week`

import kotlin.math.max
import kotlin.math.min

class `나의 인생에는 수학과 함께` {

    private var n = 0
    private val board = mutableListOf<List<String>>()
    private var maxAnswer = Int.MIN_VALUE
    private var minAnswer = Int.MAX_VALUE

    fun solve() {
        n = readln().toInt()
        repeat(n) {
            val line = readln().split(' ')
            board.add(line)
        }

        dfs(x = 0, y = 0, sum = board[0][0].toInt(), operator = null)
        print("$maxAnswer $minAnswer")
    }

    private fun dfs(x: Int, y: Int, sum: Int, operator: String?) {
        if (x == n - 1 && y == n - 1) {
            maxAnswer = max(maxAnswer, sum)
            minAnswer = min(minAnswer, sum)
            return
        }

        val downBlock = if (x + 1 != n) board[x + 1][y] else ""
        val rightBlock = if (y + 1 != n) board[x][y + 1] else ""

        if (operator != null) {
            if (downBlock.isNotEmpty()) {
                dfs(x = x + 1, y = y, sum = operator.calculate(sum, downBlock.toInt()), operator = null)
            }
            if (rightBlock.isNotEmpty()) {
                dfs(x = x, y = y + 1, sum = operator.calculate(sum, rightBlock.toInt()), operator = null)
            }
        } else {
            if (downBlock.isNotEmpty()) {
                dfs(x = x + 1, y = y, sum = sum, operator = downBlock)
            }
            if (rightBlock.isNotEmpty()) {
                dfs(x = x, y = y + 1, sum = sum, operator = rightBlock)
            }
        }
    }

    private fun String.calculate(a: Int, b: Int): Int {
        return when (this) {
            "+" -> a + b
            "-" -> a - b
            else -> a * b
        }
    }
}

fun main(): Unit = `나의 인생에는 수학과 함께`().solve()

