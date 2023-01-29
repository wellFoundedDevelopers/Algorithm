package byeonghee.`18week`

import java.io.*

class 소병희_거짓말 {

    companion object {
        var answer = 0

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val known = readLine().split(" ").map { it.toInt() }.toBitMask()
            val parties = Array(m) { readLine().split(" ").map { it.toInt() }.toBitMask() }
            var tellTheTruth = known

            var flag = true
            while(flag) {
                val prev = tellTheTruth
                for (party in parties) {
                    if ((tellTheTruth and party) != 0L) {
                        tellTheTruth = tellTheTruth or party
                    }
                }
                if (prev == tellTheTruth) flag = false
            }

            for (party in parties) {
                if (tellTheTruth and party == 0L) answer++
            }

            println(answer)
        }

        fun List<Int>.toBitMask() : Long {
            var ans = 0L
            for (i in 1 .. this[0]) {
                ans += 1L.shl(this[i] - 1)
            }
            return ans
        }
    }
}

fun main() {
    소병희_거짓말.solve()
}