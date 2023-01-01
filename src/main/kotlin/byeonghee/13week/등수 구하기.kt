package byeonghee.`13week`

import java.io.*

class 소병희_등수구하기 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        var score = 0
        var p = 0


        fun solve() {
            br.readLine().split(" ").map { it.toInt() }.let {
                n = it[0]
                score = it[1]
                p = it[2]
            }
            if (n == 0) {
                println(1)
                return
            }

            val scoreList = br.readLine().split(" ").map { it.toInt() }.plus(-1)

            var l = 0
            var r = n.coerceAtMost(p)

            while (r > l) {
                val mid = (l + r) / 2
                if (scoreList[mid] > score) l = mid + 1
                else r = mid
            }
            l = r
            while (l > 0 && scoreList[l - 1] == score) {
                l--
            }
            while (scoreList[r] == score) {
                r++
            }

            if (r >= p) println(-1)
            else println(l + 1)
        }
    }
}

fun main() {
    소병희_등수구하기.solve()
}