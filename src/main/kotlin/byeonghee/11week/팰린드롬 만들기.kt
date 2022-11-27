package byeonghee.`11week`

import java.io.*

class 소병희_팰린드롬만들기 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val alphabets = MutableList(26) { 0 }
        val sb = StringBuilder()

        fun solve() {
            br.readLine().forEach {
                alphabets[it - 'A']++
            }

            if (alphabets.count { it % 2 == 1 } > 1) {
                println("I'm Sorry Hansoo")
            }
            else {
                for (i in 'A'..'Z') {
                    sb.append("$i".repeat(alphabets[i - 'A'] / 2))
                }
                sb.append(sb.reversed())
                alphabets.indexOfFirst {  it % 2 == 1 }.let {
                    if (it != -1) sb.insert(sb.length / 2, (it + 'A'.code).toChar())
                }
                println(sb)
            }
        }
    }
}

fun main() {
    소병희_팰린드롬만들기.solve()
}