package heejik.`8week`

import java.io.BufferedReader
import java.io.InputStreamReader

class `문자열 집합` {
    val br = BufferedReader(InputStreamReader(System.`in`))

    fun solve() {

        var answer = 0
        val s = HashMap<String,Int>()
        val (n, m) = br.readLine().split(' ').map { it.toInt() }

        repeat(n) {
            s[br.readLine()] = 1
        }

        repeat(m) {
            br.readLine().run {
                if (s[this] == 1) answer += 1
            }
        }
        println(answer)
    }
}


fun main() {
    `문자열 집합`().solve()
}