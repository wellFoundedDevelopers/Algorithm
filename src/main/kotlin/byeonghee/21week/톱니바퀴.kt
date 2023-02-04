package byeonghee.`21week`

import java.io.*
import kotlin.math.abs
import kotlin.math.pow

class 소병희_톱니바퀴 {

    companion object {
        const val R = 2
        const val L = 6

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val wheels = Array(4) { readLine().toCharArray() }
            val dirs = IntArray(4)

            repeat(readLine().toInt()) {
                val (wheel, dir) = readLine().split(" ").map { it.toInt() }

                var left = wheel - 1
                while(left > 0 &&
                    wheels[left][(L + 8 - dirs[left]) % 8] != wheels[left - 1][(R + 8 - dirs[left - 1]) % 8]) {
                    left--
                }

                var right = wheel - 1
                while(right < 3 &&
                    wheels[right][(R + 8 - dirs[right]) % 8] != wheels[right + 1][(L + 8 - dirs[right + 1]) % 8]) {
                    right++
                }

                for(i in left .. right) {
                    dirs[i] = (8 + dirs[i] + dir * (-1.0).pow(abs(wheel - 1 - i)).toInt()) % 8
                }
            }

            var answer = 0
            for(i in 0 .. 3) {
                if (wheels[i][(8 - dirs[i]) % 8] == '1')
                    answer += 1 shl i
            }
            println(answer)
        }
    }
}

fun main() {
    소병희_톱니바퀴.solve()
}