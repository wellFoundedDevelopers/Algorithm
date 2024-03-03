package heejik.`52week`

import kotlin.math.max

class `도넛과 막대 그래프2` {

    fun solution(edges: Array<IntArray>): IntArray {
        val answer = IntArray(size = 4)
        val nodesReceivedCount = IntArray(size = 1000001)
        val nodesShotCount = IntArray(size = 1000001)
        var maxNode = 0
        edges.forEach {
            val (shot, receive) = it
            nodesReceivedCount[receive]++
            nodesShotCount[shot]++
            maxNode = max(maxNode, max(shot, receive))
        }


        for (i in 1..maxNode) {
            if (nodesShotCount[i] >= 2 && nodesReceivedCount[i] == 0) answer[0] = i
            if (nodesShotCount[i] == 0) answer[2]++
            if (nodesReceivedCount[i] >= 2 && nodesShotCount[i] == 2) answer[3]++
        }

        answer[1] = nodesShotCount[answer[0]] - (answer[2] + answer[3])

        return answer
    }
}