package byeonghee.week28

import java.io.*
import java.util.*
import kotlin.math.pow

class 소병희_드래곤커브 {

    companion object {
        val dr = arrayOf(0, -1, 0, 1)
        val dc = arrayOf(1, 0, -1, 0)

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val isDragon = Array(101) { BooleanArray(101) }
            val curves = LinkedList<Int>()
            var answer = 0

            var r = 0
            var c = 0
            var d = 0
            var g = 0

            curves.add(0)
            repeat(n) {
                readLine().split(" ").let {
                    c = it[0].toInt()
                    r = it[1].toInt()
                    d = it[2].toInt()
                    g = 2.0.pow(it[3].toInt()).toInt()
                }

                while(curves.size < g) {
                    for(i in curves.size - 1 downTo 0) {
                        curves.add((curves[i] + 1) % 4)
                    }
                }

                isDragon[r][c] = true
                for(i in 0 until g) {
                    r += dr[(d + curves[i]) % 4]
                    c += dc[(d + curves[i]) % 4]
                    isDragon[r][c] = true
                }
            }

            for(i in 0 until 100) for(j in 0 until 100) {
                if (isDragon[i][j] && isDragon[i][j + 1] && isDragon[i + 1][j] && isDragon[i + 1][j + 1]) answer++
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_드래곤커브.solve()
}