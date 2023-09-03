package byeonghee.week43

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_겹치는건싫어 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val nums = IntArray(n)
            val count = IntArray(100001)
            var p1 = 0
            var p2 = 0
            var ans = 0

            readLine().split(" ").forEachIndexed { i, v ->
                nums[i] = v.toInt()
            }

            while(p2 < n) {
                count[nums[p2]]++
                while(count[nums[p2]] > k && p1 < p2)
                    count[nums[p1++]]--

                p2++
                ans = ans.coerceAtLeast(p2 - p1)
            }

            println(ans)
        }
    }
}

fun main() {
    소병희_겹치는건싫어.solve()
}