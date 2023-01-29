package byeonghee.`19week`

import java.io.*

class 소병희_2nx타일링 {

    companion object {
        const val MOD = 10007

        fun solve_matrix(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            var exponent = readLine().toInt()
            var baseMatrix = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
            var ansMatrix = baseMatrix.copyOf()

            if (exponent < 2) {
                println(1)
                return
            }

            exponent -= 2
            while(exponent > 0) {
                if (exponent % 2 == 1) {
                    ansMatrix = baseMatrix.productWith(ansMatrix)
                }
                exponent /= 2
                baseMatrix = baseMatrix.productWith(baseMatrix)
            }
            println(ansMatrix.productWith(Array(2) {intArrayOf(1) })[0][0])
        }

        fun Array<IntArray>.productWith(a: Array<IntArray>): Array<IntArray> {
            val ret = Array(this.size) { IntArray(a[0].size) }
            for (r in this.indices) for (c in a[0].indices) for(k in this[0].indices) {
                ret[r][c] = (ret[r][c] + ((this[r][k] * a[k][c]) % MOD)) % MOD
            }
            return ret
        }

        fun solve_dp(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val dp = IntArray(n + 1)

            dp[0] = 1
            dp[1] = 1
            for(i in 2 .. n) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD
            }

            println(dp[n])
        }
    }
}

fun main() {
    소병희_2nx타일링.solve_dp()
    소병희_2nx타일링.solve_matrix()
}