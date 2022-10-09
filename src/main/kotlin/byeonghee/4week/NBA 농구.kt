package byeonghee.`4week`

import java.io.*

class `소병희_NBA 농구` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        data class Goal(val team: Int, val time: Int)
        val goals = mutableListOf<Goal>()

        val maxT = 48 * 60
        val winningT = IntArray(2)
        var markTeam = 0
        var markTime = 0

        fun timeToInt(time: String) : Int {
            val (m, s) = time.split(":").map { it.first().digitToInt() * 10 + it.last().digitToInt() }
            return m * 60 + s
        }

        fun intToTime(time: Int) : String {
            val m = (time / 60).toString().padStart(2, '0')
            val s = (time % 60).toString().padStart(2, '0')
            return "$m:$s"
        }

        fun teamCount(team: Int) = if (team == 1) 1 else -1

        fun solution() {
            repeat(br.readLine().toInt()) {
                br.readLine().split(" ").run {
                    goals.add(Goal(first().toInt(), timeToInt(last())))
                }
            }
            goals.sortBy{ it.time }

            for((goalTeam, goalTime) in goals) {
                markTime = if (markTeam == 0) goalTime else markTime
                markTeam += teamCount(goalTeam)

                if(markTeam == 0) {
                    winningT[goalTeam % 2] += (goalTime - markTime)
                    markTime = goalTime
                }
            }

            if(markTeam > 0) {
                winningT[0] += (maxT - markTime)
            }
            else if (markTeam < 0) {
                winningT[1] += (maxT - markTime)
            }

            println(winningT.joinToString("\n") { intToTime(it) })
        }
    }
}

fun main() {
    `소병희_NBA 농구`.getSolution().solution()
}