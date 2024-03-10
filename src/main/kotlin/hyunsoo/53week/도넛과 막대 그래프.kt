package hyunsoo.`53week`

/**
 *
 * <문제>
 * []()
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_도넛과_막대_그래프` {

    fun solution(edges: Array<IntArray>): IntArray {

        val outNodesFromHere = Array(edges.size + 1) {
            mutableListOf<Int>()
        }

        val inNodesToHere = Array(edges.size + 1) {
            mutableListOf<Int>()
        }

        val answer: IntArray = intArrayOf(0, 0, 0, 0)
        var totalGraphCnt = 0

        edges.forEach {
            val (from, to) = it

            outNodesFromHere[from].add(to)
            inNodesToHere[to].add(from)
        }

        for (nodeIndex in 1..edges.size) {

            val outCnt = outNodesFromHere[nodeIndex].size
            val inCnt = inNodesToHere[nodeIndex].size

            // 정점
            if (outCnt >= 2 && inCnt == 0) {
                answer[0] = nodeIndex
                totalGraphCnt = outCnt
                // 막대
            } else if (outCnt == 0 && inCnt >= 1) {
                answer[2]++
                // 8자
            } else if (outCnt >= 2 && inCnt >= 2) {
                answer[3]++
            }

        }

        val curGraphCnt = answer[2] + answer[3]

        if (totalGraphCnt > curGraphCnt) answer[1] = totalGraphCnt - curGraphCnt

        return answer

    }
}

fun main() {

    전현수_도넛과_막대_그래프().solution(
        arrayOf(
            intArrayOf(2, 3),
            intArrayOf(4, 3),
            intArrayOf(1, 1),
            intArrayOf(2, 1),
        )
    ).apply {
        println(this.toList())
    }
}