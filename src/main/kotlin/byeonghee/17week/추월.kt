package byeonghee.`17week`

import java.io.*

class 소병희_추월 {

    companion object {
        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            var answer = 0
            val passedMap = HashMap<String, Boolean>()

            val inCar = List(n) { readLine() }
            var inIdx = 0
            repeat(n) { outIdx ->
                val passing = readLine()
                while (passedMap.containsKey(inCar[inIdx])) inIdx++
                if (passing != inCar[inIdx]) {
                    passedMap[passing] = true
                    answer++
                }
                else inIdx++
            }
            println(answer)
        }
    }
}

fun main() {
    소병희_추월.solve()
}