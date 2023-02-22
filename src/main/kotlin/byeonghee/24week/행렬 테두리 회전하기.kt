class 소병희_행렬테두리회전하기 {
    fun solution(rows: Int, cols: Int, queries: Array<IntArray>): IntArray {
        val matrix = Array(rows) { r -> IntArray(cols) { c -> r * rows + c + 1 }}

        val answer = mutableListOf<Int>()

        for(i in queries.indices) {
            val (r1, c1, r2, c2) = queries[i].map { it - 1}

            val tmp = matrix[r1][c1]
            var smallest = tmp

            for(r in r1 until r2) {
                matrix[r][c1] = matrix[r + 1][c1]
                smallest = smallest.coerceAtMost(matrix[r][c1])
            }

            for(c in c1 until c2) {
                matrix[r2][c] = matrix[r2][c + 1]
                smallest = smallest.coerceAtMost(matrix[r2][c])
            }

            for(r in r2 downTo r1 + 1) {
                matrix[r][c2] = matrix[r - 1][c2]
                smallest = smallest.coerceAtMost(matrix[r][c2])
            }

            for(c in c2 downTo c1 + 2) {
                matrix[r1][c] = matrix[r1][c - 1]
                smallest = smallest.coerceAtMost(matrix[r1][c])
            }

            smallest = smallest.coerceAtMost(matrix[r1][c1 + 1])
            matrix[r1][c1 + 1] = tmp
            answer.add(smallest)
        }

        return answer.toIntArray()
    }
}
