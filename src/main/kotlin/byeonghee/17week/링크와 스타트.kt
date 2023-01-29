package byeonghee.`17week`

import java.io.*
import kotlin.math.*

class 소병희_링크와스타트 {

    companion object {
        lateinit var teamStart : IntArray
        lateinit var teamLink : IntArray
        lateinit var synergy : Array<List<Int>>
        var answer = Int.MAX_VALUE
        var n = 0
        var startN = 1
        var linkN = 0

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            n = readLine().toInt()
            synergy = Array(n) { readLine().split(" ").map { it.toInt() }}

            teamStart = IntArray(n)
            teamLink = IntArray(n)

            splitTeam(1)
            println(answer)
        }

        fun splitTeam(curN: Int) {
            if(curN == n) {
                var curAns = 0
                for(i in 0 until startN) for(j in 0 until startN){
                    curAns += synergy[teamStart[i]][teamStart[j]]
                }
                for(i in 0 until linkN) for(j in 0 until linkN){
                    curAns -= synergy[teamLink[i]][teamLink[j]]
                }
                answer = min(answer, abs(curAns))
                return
            }

            if (startN < n) {
                teamStart[startN++] = curN
                splitTeam(curN + 1)
                startN--
            }
            teamLink[linkN++] = curN
            splitTeam(curN + 1)
            linkN--
        }
    }
}

fun main() {
    소병희_링크와스타트.solve()
}