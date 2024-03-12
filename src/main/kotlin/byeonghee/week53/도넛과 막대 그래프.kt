package byeonghee.week53

class 소병희_도넛과막대그래프 {
    fun solution(edges: Array<IntArray>): IntArray {
        val answer = IntArray(4)
        val inDegree = IntArray(1_000_001)
        val outDegree = IntArray(1_000_001)

        for((from, to) in edges) {
            outDegree[from]++
            inDegree[to]++
        }

        var v = 1
        while(inDegree[v] > 0 || outDegree[v] > 0) {
            if (inDegree[v] == 0 && outDegree[v] >= 2) answer[0] = v
            else if (outDegree[v] == 0) answer[2]++
            else if (inDegree[v] >= 2 && outDegree[v] == 2) answer[3]++

            v++
        }

        answer[1] = outDegree[answer[0]] - answer[2] - answer[3]

        return answer
    }
}