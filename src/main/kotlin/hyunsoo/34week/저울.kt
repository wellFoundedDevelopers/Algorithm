package hyunsoo.`34week`

/**
 *
 * <문제>
 * [저울](https://www.acmicpc.net/problem/10159)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_저울` {

    fun solution() {

        val stuffCnt = readln().toInt()
        val pairCnt = readln().toInt()

        val stuffTable = Array(stuffCnt + 1) {
            BooleanArray(stuffCnt + 1)
        }

        repeat(pairCnt) {
            val (start, end) = readln().split(" ").map { it.toInt() }
            stuffTable[start][end] = true
        }

        for (target in 1..stuffCnt) {
            for (start in 1..stuffCnt) {
                for (end in 1..stuffCnt) {
                    if (stuffTable[start][target] && stuffTable[target][end]) stuffTable[start][end] = true
                }
            }
        }

        for (start in 1..stuffCnt) {
            var cnt = 0
            for (end in 1..stuffCnt) {
                if (start == end) continue
                if (stuffTable[start][end].not() && stuffTable[end][start].not()) cnt++
            }
            println(cnt)
        }

    }
}

fun main() {
    전현수_저울().solution()
}