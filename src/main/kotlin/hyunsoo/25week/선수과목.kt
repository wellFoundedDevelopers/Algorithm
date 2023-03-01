package hyunsoo.`25week`

/**
 *
 * <문제>
 * [선수과목](https://www.acmicpc.net/problem/14567)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_선수과목` {

    fun solution() {

        val queue = ArrayDeque<Int>()

        val (subjectCnt, conditionCnt) = readln().split(" ").map { it.toInt() }

        val inDegree = IntArray(subjectCnt)
        val subjectTree = Array(subjectCnt) {
            mutableListOf<Int>()
        }

        val needSemesterCnt = IntArray(subjectCnt)

        repeat(conditionCnt) {
            val (fir, sec) = readln().split(" ").map { it.toInt() - 1 }
            subjectTree[fir].add(sec)
            inDegree[sec]++
        }

        var semester = 1
        while (inDegree.any { it != -1 }) {

            for (targetNode in 0 until subjectCnt) {
                if (inDegree[targetNode] == 0) {
                    queue.add(targetNode)
                    inDegree[targetNode] = -1
                    needSemesterCnt[targetNode] = semester
                }
            }

            while (queue.isNotEmpty()) {
                val target = queue.removeLast()
                subjectTree[target].forEach { degree ->
                    inDegree[degree]--
                }
            }

            semester++

        }

        needSemesterCnt.joinToString(" ").apply {
            println(this)
        }

    }
}

fun main() {
    전현수_선수과목().solution()
}