package byeonghee.week43

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_회전초밥 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, d, k, c) = readLine().split(" ").map { it.toInt() }
            val sushi = IntArray(n) { readLine().toInt() }
            val eaten = IntArray(3001)

            var kind = 1
            var ans = 0
            var p1 = 0
            eaten[c]++

            for(p2 in 0 until n + k - 1) {
                if (eaten[sushi[p2%n]]++ == 0) kind++
                if (p2 - p1 + 1 < k) continue

                ans = ans.coerceAtLeast(kind)
                if (--eaten[sushi[p1%n]] == 0) kind--
                p1++
            }

            println(ans)
        }
    }
}

fun main() {
    소병희_회전초밥.solve()
}