package byeonghee.week29

import java.io.StreamTokenizer

class 소병희_컨베이어벨트위의로봇 {

    companion object {
        const val ROBOT = 0
        const val DURAB = 1

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val k = input()
            var broken = 0
            var answer = 0

            val belt = Array(2 * n + 1) { IntArray(2) }
            repeat(2 * n) { belt[it+1][DURAB] = input() }

            fun rotateBelt() {
                belt[0] = belt[2 * n]
                for(i in 2 * n downTo 1) {
                    belt[i] = belt[i - 1]
                }
                belt[n][ROBOT] = 0
            }

            fun moveRobots() {
                for(i in n-1 downTo 1) {
                    if (belt[i][ROBOT] == 1 && belt[i+1][DURAB] > 0 && belt[i+1][ROBOT] == 0) {
                        belt[i][ROBOT] = 0
                        belt[i+1][ROBOT] = 1
                        if(--belt[i+1][DURAB] == 0) broken++
                    }
                }
                belt[n][ROBOT] = 0
            }

            while(broken < k) {
                rotateBelt()
                moveRobots()
                if (belt[1][DURAB] > 0) {
                    belt[1][ROBOT] = 1
                    if(--belt[1][DURAB] == 0) broken++
                }
                answer++
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_컨베이어벨트위의로봇.solve()
}