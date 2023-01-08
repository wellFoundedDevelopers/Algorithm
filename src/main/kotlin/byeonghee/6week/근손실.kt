package byeonghee.`6week`

import java.io.*

class `소병희_근손실` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        var k = 0
        var workouts = IntArray(0)

        val visited = BooleanArray(8) { false }
        var curPerm = 0
        var answer = 0

        fun solution() {
            br.readLine().split(" ").map { it.toInt() }.run {
                n = first()
                k = last()
            }
            workouts = br.readLine().split(" ").map { it.toInt() - k }.toIntArray()

            makePerm(0)
            println(answer)
        }

        fun makePerm(len: Int) {
            if (len == n) {
                answer++
                return
            }

            for(i in 0 until n) {
                if (visited[i]) continue
                if (curPerm + workouts[i] < 0) continue

                visited[i] = true
                curPerm += workouts[i]
                makePerm(len + 1)
                curPerm -= workouts[i]
                visited[i] = false
            }
        }
    }
}

fun main() {
    `소병희_근손실`.getSolution().solution()
}