package byeonghee.`8week`

import java.io.*

class `소병희_문자열 집합` {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val hashMap = HashMap<String, Boolean>()
    var answer = 0

    fun solution() {
        val (n, m) = br.readLine().split(" ").map{ it.toInt() }

        repeat(n) {
            hashMap[br.readLine()] = true
        }

        repeat(m) {
            if (hashMap[br.readLine()] != null) answer++
        }

        println(answer)
    }
}

fun main() {
    `소병희_문자열 집합`().solution()
}