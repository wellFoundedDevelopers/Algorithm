package byeonghee.week43

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_기타리스트 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, s, m) = readLine().split(" ").map { it.toInt() }
            val songs = readLine().split(" ").map { it.toInt() }
            val volume = Array(n) { BooleanArray(m+1) }

            if (s - songs[0] >= 0) volume[0][s - songs[0]] = true
            if (s + songs[0] <= m) volume[0][s + songs[0]] = true

            for(i in 1 until n) for(j in 0 .. m){
                if (volume[i-1][j]) {
                    if (j - songs[i] >= 0) volume[i][j - songs[i]] = true
                    if (j + songs[i] <= m) volume[i][j + songs[i]] = true
                }
            }

            for(j in m downTo 0) {
                if (volume[n-1][j]) {
                    println(j)
                    return@with
                }
            }

            println(-1)
        }
    }
}

fun main() {
    소병희_기타리스트.solve()
}