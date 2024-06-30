package byeonghee.week67

class 소병희_테이블해시함수 {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        var answer = 0

        data.sortWith(compareBy<IntArray> { it[col-1] }.thenByDescending { it[0] })

        for(i in row_begin .. row_end) {
            var si = 0
            for(num in data[i-1]) {
                si += num % i
            }

            answer = answer xor si
        }

        return answer
    }
}