package byeonghee.`22week`

import java.io.*

class 소병희_ {

    companion object {
        const val T = 'T'
        const val S = 'S'
        const val O = 'O'
        const val X = 'X'

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val hallway = List(n) { readLine().replace(" ", "").toCharArray() }
            var answer = "NO"

            fun checkHallway() {
                for(i in 0 until n) {
                    var rowCheck = '.'
                    var colCheck = '.'
                    for(j in 0 until n) {
                        when(hallway[i][j]) {
                            S -> if (rowCheck == T) return else rowCheck = S
                            T -> if (rowCheck == S) return else rowCheck = T
                            O -> rowCheck = O
                        }

                        when(hallway[j][i]) {
                            S -> if (colCheck == T) return else colCheck = S
                            T -> if (colCheck == S) return else colCheck = T
                            O -> colCheck = O
                        }
                    }
                }
                answer = "YES"
            }

            fun putObstacle(s: Int, put: Int) {
                if (put == 3) {
                    checkHallway()
                    return
                }
                for(i in s until n * n) {
                    if (hallway[i / n][i % n] == X) {
                        hallway[i / n][i % n] = O
                        putObstacle(s + 1, put + 1)
                        if (answer == "YES") return
                        hallway[i / n][i % n] = X
                    }
                }
            }

            putObstacle(0, 0)
            println(answer)
        }
    }
}

fun main() {
    소병희_.solve()
}