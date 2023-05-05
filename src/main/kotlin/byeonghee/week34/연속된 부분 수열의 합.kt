package byeonghee.week34

class 소병희_연속된부분수열의합 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val answer = IntArray(2)
        val last = sequence.lastIndex
        var diff = sequence.size
        var p1 = 0
        var p2 = -1
        var sum = 0

        while(p1 < last) {
            if (sum < k && p2 < last) sum += sequence[++p2]
            if (p2 == last && sum < k) break
            if (sum > k) sum -= sequence[p1++]
            if (sum == k) {
                if (p2 - p1 < diff) {
                    answer[0] = p1
                    answer[1] = p2
                    diff = p2 - p1
                    if (diff == 0) break
                }
                sum -= sequence[p1++]
            }
        }

        return answer
    }
}

//class 소병희_연속된부분수열의합_old {
//    fun solution(sequence: IntArray, k: Int): IntArray {
//        val answer = intArrayOf(0, sequence.lastIndex)
//        var sum = 0
//        var p1 = 0
//        for(p2 in sequence.indices) {
//            sum += sequence[p2]
//            while(sum > k && p1 < p2) sum -= sequence[p1++]
//            if (sum == k) {
//                if (p2-p1 < answer[1] - answer[0]) {
//                    answer[0] = p1
//                    answer[1] = p2
//                }
//                if (p2 == p1) break
//                sum -= sequence[p1++]
//            }
//        }
//
//        return answer
//    }
//}