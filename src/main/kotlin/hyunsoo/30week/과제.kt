package hyunsoo.`30week`

/**
 *
 * <문제>
 * [과제](https://www.acmicpc.net/problem/13904)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_과제` {

    private data class Assignment(val remain: Int, val score: Int)

    private val assignmentList = mutableListOf<Assignment>()
    private var score = 0

    fun solution() {

        val n = readln().toInt()

        repeat(n) {
            readln().split(" ").map { it.toInt() }.apply {
                assignmentList.add(
                    Assignment(this.first(), this.last())
                )
            }
        }

        repeat(n) { index ->

            val curDay = n - index

            val target = assignmentList.filter {
                curDay <= it.remain
            }.maxByOrNull {
                it.score
            }

            target?.let {
                score += it.score
                assignmentList.remove(it)
            }
        }

        println(score)
    }

}

fun main() {
    전현수_과제().solution()
}