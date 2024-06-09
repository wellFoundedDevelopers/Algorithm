package byeonghee.week66

class 소병희_용액 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val q = readLine().split(" ").map { it.toInt() }.sorted()

            val answer = IntArray(3) { Int.MAX_VALUE } // { 현재까지 절대값최소합, 용액1의 값, 용액2의 값 }
            var minP = 0
            var maxP = n - 1

            while(maxP > minP) {
                val newSum = q[maxP] + q[minP]
                if (kotlin.math.abs(newSum) < answer[0]) {
                    answer[0] = kotlin.math.abs(newSum)
                    answer[1] = q[minP]
                    answer[2] = q[maxP]
                }

                if (newSum > 0) {
                    maxP--
                }
                else if (newSum < 0) {
                    minP++
                }
                else break
            }

            println("${answer[1]} ${answer[2]}")
        }
    }
}

fun main() {
    소병희_용액.solve()
}