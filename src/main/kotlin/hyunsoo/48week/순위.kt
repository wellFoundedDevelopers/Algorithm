package hyunsoo.`48week`

/**
 *
 * <문제>
 * [순위](https://school.programmers.co.kr/learn/courses/30/lessons/49191)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_순위` {

    fun solution(n: Int, results: Array<IntArray>): Int {

        val resultTable = Array(n + 1) {
            IntArray(n + 1) { INIT }
        }

        results.forEach { result ->
            val (winPos, losePos) = result
            resultTable[winPos][losePos] = WIN
            resultTable[losePos][winPos] = LOSE
        }

        for (k in 1 .. n) {
            for (i in 1 .. n) {
                for (j in 1 .. n) {
                    if (resultTable[i][k] == WIN && resultTable[k][j] == WIN) {
                        resultTable[i][j] = WIN
                        resultTable[j][i] = LOSE
                    }
                }
            }
        }

        return resultTable.drop(1).count { row ->
            row.drop(1).count { it == INIT} <= 1
        }
    }

    companion object {
        const val WIN = 1
        const val LOSE = -1
        const val INIT = 0
    }
}

fun main() {
    전현수_순위().solution(
        5, arrayOf(
            intArrayOf(4,3),
            intArrayOf(4,2),
            intArrayOf(3,2),
            intArrayOf(1,2),
            intArrayOf(2,5),
        )
    ).apply {
        println(this)
    }
}